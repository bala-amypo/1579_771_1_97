package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String verificationCode;

    // Getters
    public Long getId() { return id; }
    public String getVerificationCode() { return verificationCode; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Certificate c = new Certificate();
        public Builder id(Long id) { c.id = id; return this; }
        public Builder verificationCode(String code) { c.verificationCode = code; return this; }
        public Certificate build() { return c; }
    }
}
