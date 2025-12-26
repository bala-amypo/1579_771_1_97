// src/main/java/com/example/demo/entity/Student.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"email"}),
  @UniqueConstraint(columnNames = {"rollNumber"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String rollNumber;
}
