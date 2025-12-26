package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private final Key key;
    private final Long expirationMs;

    public JwtUtil(String secret, Long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(Map<String,Object> claims, String subject) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder().setClaims(claims).setSubject(subject)
            .setIssuedAt(now).setExpiration(exp)
            .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token) {
        try { parseToken(token); return true; }
        catch (Exception e) { return false; }
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
