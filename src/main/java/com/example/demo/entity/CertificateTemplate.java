// src/main/java/com/example/demo/entity/CertificateTemplate.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certificate_templates", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"templateName"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateTemplate {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String templateName;

  @Column(nullable = false)
  private String backgroundUrl;

  private String fontStyle;
  private String signatureName;
}
