package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
  private final String secret;
  private final Long expirationMs;
  private final Key key;

  // Required constructor signature
  public JwtUtil(String secret, Long expirationMs) {
    this.secret = secret;
    this.expirationMs = expirationMs;
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(Map<String, Object> claims, String subject) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + expirationMs);
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(now)
      .setExpiration(exp)
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      parseToken(token);
      return true;
    } catch (JwtException | IllegalArgumentException ex) {
      return false;
    }
  }

  public Jws<Claims> parseToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token);
  }
}
