package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "certificate_templates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateTemplate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 63]
    @Column(unique = true)
    private String templateName; [cite: 64]
    private String backgroundUrl; [cite: 65]
    private String fontStyle; [cite: 66]
    private String signatureName; [cite: 67]
}