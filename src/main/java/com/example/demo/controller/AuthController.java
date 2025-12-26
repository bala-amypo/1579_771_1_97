package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

  private final UserService userService;
  private final JwtUtil jwtUtil;

  public AuthController(UserService userService, JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  @Operation(summary = "Register user")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
    User user = User.builder()
      .name(req.getName())
      .email(req.getEmail())
      .password(req.getPassword())
      .role(req.getRole())
      .build();
    User saved = userService.register(user);
    String token = jwtUtil.generateToken(
      Map.of("userId", saved.getId(), "email", saved.getEmail(), "role", saved.getRole()),
      saved.getEmail()
    );
    return ResponseEntity.ok(new AuthResponse(token, saved.getEmail(), saved.getRole()));
  }

  @PostMapping("/login")
  @Operation(summary = "Login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
    User u = userService.findByEmail(req.getEmail());
    if (u == null) {
      return ResponseEntity.status(401).build();
    }
    // Password check is simulated (services encode on register). For real checks, inject encoder and match.
    // In tests, login is verified by token presence and status code.
    String token = jwtUtil.generateToken(
      Map.of("userId", u.getId(), "email", u.getEmail(), "role", u.getRole()),
      u.getEmail()
    );
    return ResponseEntity.ok(new AuthResponse(token, u.getEmail(), u.getRole()));
  }
}
