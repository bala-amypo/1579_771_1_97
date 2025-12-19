package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certificates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private CertificateTemplate template;

    private LocalDate issuedDate;

    @Column(unique = true)
    private String verificationCode;

    private String qrCodeUrl;

    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
    private List<VerificationLog> logs;
}
