// src/main/java/com/example/demo/dto/RegisterRequest.java
package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterRequest {
  private String name;
  private String email;
  private String password;
  private String role; // optional; default STAFF
}
