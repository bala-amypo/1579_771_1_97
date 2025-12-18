package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Entity
@Table(name = "certificates")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 82]
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student; [cite: 83, 91]
    @ManyToOne
    @JoinColumn(name = "template_id")
    private CertificateTemplate template; [cite: 84, 91]
    private LocalDate issuedDate; [cite: 85]
    @Column(columnDefinition = "TEXT")
    private String qrCodeUrl; [cite: 86]
    @Column(unique = true)
    private String verificationCode; [cite: 87]
}