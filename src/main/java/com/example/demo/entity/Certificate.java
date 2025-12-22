package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private CertificateTemplate template;

    private LocalDate issuedDate;

    @Column(unique = true, nullable = false)
    private String verificationCode;

    private String qrCodeUrl;

    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL)
    private List<VerificationLog> logs;

    public Certificate() {}

    public Certificate(Long id, Student student, CertificateTemplate template, LocalDate issuedDate,
                       String verificationCode, String qrCodeUrl) {
        this.id = id;
        this.student = student;
        this.template = template;
        this.issuedDate = issuedDate;
        this.verificationCode = verificationCode;
        this.qrCodeUrl = qrCodeUrl;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public CertificateTemplate getTemplate() { return template; }
    public void setTemplate(CertificateTemplate template) { this.template = template; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }

    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }

    public List<VerificationLog> getLogs() { return logs; }
    public void setLogs(List<VerificationLog> logs) { this.logs = logs; }
}
