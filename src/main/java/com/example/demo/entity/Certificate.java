package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "certificates",
  uniqueConstraints = @UniqueConstraint(columnNames = "verificationCode"))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  private Student student;

  @ManyToOne(optional = false)
  private CertificateTemplate template;

  @Column(nullable = false, unique = true)
  private String verificationCode;

  @Column(length = 4000)
  private String qrCodeUrl;

  private LocalDate issuedDate;
}
