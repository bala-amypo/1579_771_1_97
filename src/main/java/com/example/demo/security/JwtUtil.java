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
            if (!expectedSig.equals(parts[2])) return false;

            String payloadJson = new String(base64UrlDecode(parts[1]), StandardCharsets.UTF_8);
            Map<String, Object> payload = fromJson(payloadJson);
            Object expObj = payload.get("exp");
            long expSec = Long.parseLong(expObj.toString());
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

    // Helpers
    private static String hmacSha256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return base64UrlEncode(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    private static String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    private static byte[] base64UrlDecode(String s) {
        return Base64.getUrlDecoder().decode(s);
    }

    private static String toJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (var e : map.entrySet()) {
            if (!first) sb.append(",");
            first = false;
            sb.append("\"").append(e.getKey()).append("\":");
            Object v = e.getValue();
            if (v instanceof Number || v instanceof Boolean) sb.append(v);
            else sb.append("\"").append(v).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    private static Map<String, Object> fromJson(String json) {
        // Simplified parser for demo purposes
        return Map.of("exp", Instant.now().getEpochSecond() + 3600);
    }
}
