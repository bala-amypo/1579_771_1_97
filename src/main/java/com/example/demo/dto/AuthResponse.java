package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private Long id;
    private String email;
    private String role;

    // No-args constructor
    public AuthResponse() {}

    // All-args constructor
    public AuthResponse(String token, Long id, String email, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }

    // Getters
    public String getToken() { return token; }
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }

    // Setters
    public void setToken(String token) { this.token = token; }
    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}
