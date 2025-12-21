package com.example.demo.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

public class JwtUtil {

    private final String secret;
    private final Long expirationMs;

    public JwtUtil(String secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        try {
            String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
            long expMillis = Instant.now().toEpochMilli() + expirationMs;
            long expSec = expMillis / 1000;

            Map<String, Object> payload = new LinkedHashMap<>(claims);
            payload.put("sub", subject);
            payload.put("exp", expSec);

            String payloadJson = toJson(payload);
            String headerB64 = base64UrlEncode(headerJson.getBytes(StandardCharsets.UTF_8));
            String payloadB64 = base64UrlEncode(payloadJson.getBytes(StandardCharsets.UTF_8));
            String unsignedToken = headerB64 + "." + payloadB64;
            String signature = hmacSha256(unsignedToken, secret);
            return unsignedToken + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Token generation failed");
        }
    }

    public boolean validateToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return false;
            String unsigned = parts[0] + "." + parts[1];
            String expectedSig = hmacSha256(unsigned, secret);
            if (!constantTimeEquals(expectedSig, parts[2])) return false;

            String payloadJson = new String(base64UrlDecode(parts[1]), StandardCharsets.UTF_8);
            Map<String, Object> payload = fromJson(payloadJson);
            Object expObj = payload.get("exp");
            if (expObj == null) return false;
            long expSec = (expObj instanceof Number) ? ((Number) expObj).longValue() : Long.parseLong(expObj.toString());
            long nowSec = Instant.now().getEpochSecond();
            return nowSec < expSec;
        } catch (Exception e) {
            return false;
        }
    }

    public Map<String, Object> parseToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return Map.of();
            String payloadJson = new String(base64UrlDecode(parts[1]), StandardCharsets.UTF_8);
            return fromJson(payloadJson);
        } catch (Exception e) {
            return Map.of();
        }
    }

    private static String hmacSha256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] raw = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return base64UrlEncode(raw);
    }

    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    private static byte[] base64UrlDecode(String s) {
        return Base64.getUrlDecoder().decode(s);
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a.length() != b.length()) return false;
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            res |= a.charAt(i) ^ b.charAt(i);
        }
        return res == 0;
    }
    private static String toJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if (!first) sb.append(",");
            first = false;
            sb.append("\"").append(escape(e.getKey())).append("\":");
            Object v = e.getValue();
            if (v == null) {
                sb.append("null");
            } else if (v instanceof Number || v instanceof Boolean) {
                sb.append(v.toString());
            } else {
                sb.append("\"").append(escape(String.valueOf(v))).append("\"");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static Map<String, Object> fromJson(String json) 
    {
        Map<String, Object> map = new LinkedHashMap<>();
        String trimmed = json.trim();
        if (trimmed.length() < 2 || trimmed.charAt(0) != '{' || trimmed.charAt(trimmed.length() - 1) != '}') 
        {
            return map;
        }
        String body = trimmed.substring(1, trimmed.length() - 1).trim();
        if (body.isEmpty()) return map;

        int i = 0;
        while (i < body.length()) 
        {
            while (i < body.length() && Character.isWhitespace(body.charAt(i))) i++;
            if (body.charAt(i) != '"') break;
            int endKey = body.indexOf('"', i + 1);
            String key = unescape(body.substring(i + 1, endKey));
            i = endKey + 1;
            while (i < body.length() && (Character.isWhitespace(body.charAt(i)) || body.charAt(i) == ':')) 
            {
                if (body.charAt(i) == ':') { i++; break; }
                i++;
            }
            while (i < body.length() && Character.isWhitespace(body.charAt(i))) i++;
            char c = body.charAt(i);
            Object value;
            if (c == '"') 
            {
                int endVal = body.indexOf('"', i + 1);
                value = unescape(body.substring(i + 1, endVal));
                i = endVal + 1;
            } 
            else 
            {
                int endVal = i;
                while (endVal < body.length() && ",}".indexOf(body.charAt(endVal)) == -1) endVal++;
                String raw = body.substring(i, endVal).trim();
                if (raw.equals("null")) 
                {
                    value = null;
                }
                 else if (raw.equals("true") || raw.equals("false")) 
                {
                    value = Boolean.parseBoolean(raw);
                } 
                else 
                {
                    try 
                    {
                        if (raw.contains("."))
                            value = Double.parseDouble(raw);
                        else
                            value = Long.parseLong(raw);
                    } 
                    catch (NumberFormatException ex) 
                    {
                        value = raw;
                    }
                }
                i = endVal;
            }
            map.put(key, value);
            while (i < body.length() && Character.isWhitespace(body.charAt(i))) i++;
            if (i < body.length() && body.charAt(i) == ',') i++;
            while (i < body.length() && Character.isWhitespace(body.charAt(i))) i++;
        }
        return map;
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String unescape(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
