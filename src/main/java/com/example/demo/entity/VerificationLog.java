package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "verification_logs")
public class VerificationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Certificate certificate;

    // Getters
    public Long getId() { return id; }
    public Certificate getCertificate() { return certificate; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCertificate(Certificate certificate) { this.certificate = certificate; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final VerificationLog v = new VerificationLog();
        public Builder id(Long id) { v.id = id; return this; }
        public Builder certificate(Certificate cert) { v.certificate = cert; return this; }
        public VerificationLog build() { return v; }
    }
}
