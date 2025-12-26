// src/main/java/com/example/demo/entity/Certificate.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificates", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"verificationCode"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "template_id")
  private CertificateTemplate template;

  private LocalDate issuedDate;

  @Column(length = 8192)
  private String qrCodeUrl;

  @Column(nullable = false, unique = true)
  private String verificationCode;
}
