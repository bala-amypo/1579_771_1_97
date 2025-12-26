// src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

  private final UserService userService;
  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public AuthController(UserService userService, JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterRequest req) {
    User toSave = User.builder()
      .name(req.getName())
      .email(req.getEmail())
      .password(req.getPassword())
      .role(req.getRole())
      .build();
    User saved = userService.register(toSave);
    return ResponseEntity.ok(saved);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest req) {
    User user = userService.findByEmail(req.getEmail());
    if (user == null || !encoder.matches(req.getPassword(), user.getPassword())) {
      return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }
    Map<String, Object> claims = Map.of(
      "userId", user.getId(),
      "email", user.getEmail(),
      "role", user.getRole()
    );
    String token = jwtUtil.generateToken(claims, user.getEmail());
    return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getEmail(), user.getRole()));
  }
}
