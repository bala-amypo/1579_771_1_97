// src/main/java/com/example/demo/entity/User.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"email"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  private String role; // ADMIN / STAFF
}
