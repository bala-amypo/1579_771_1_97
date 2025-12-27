package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String verificationCode;

    @ManyToOne
    private Student student;

    @ManyToOne
    private CertificateTemplate template;

    private LocalDate issuedDate;
    private String qrCodeUrl;

    // Getters/Setters
    public Long getId() { return id; }
    public String getVerificationCode() { return verificationCode; }
    public Student getStudent() { return student; }
    public CertificateTemplate getTemplate() { return template; }
    public LocalDate getIssuedDate() { return issuedDate; }
    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setId(Long id) { this.id = id; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
    public void setStudent(Student student) { this.student = student; }
    public void setTemplate(CertificateTemplate template) { this.template = template; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Certificate c = new Certificate();
        public Builder id(Long id) { c.id = id; return this; }
        public Builder verificationCode(String code) { c.verificationCode = code; return this; }
        public Builder student(Student s) { c.student = s; return this; }
        public Builder template(CertificateTemplate t) { c.template = t; return this; }
        public Builder issuedDate(LocalDate date) { c.issuedDate = date; return this; }
        public Builder qrCodeUrl(String url) { c.qrCodeUrl = url; return this; }
        public Certificate build() { return c; }
    }
}
