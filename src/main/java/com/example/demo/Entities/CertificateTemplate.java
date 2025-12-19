package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "certificate_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateTemplate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String templateName;

    private String backgroundUrl;
    private String fontStyle;
    private String signatureName;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
    private List<Certificate> certificates;
}
