package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String verificationCode;

    @ManyToOne
    private Student student;

    // Getters
    public Long getId() { return id; }
    public String getVerificationCode() { return verificationCode; }
    public Student getStudent() { return student; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
    public void setStudent(Student student) { this.student = student; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Certificate c = new Certificate();
        public Builder id(Long id) { c.id = id; return this; }
        public Builder verificationCode(String code) { c.verificationCode = code; return this; }
        public Builder student(Student s) { c.student = s; return this; }
        public Certificate build() { return c; }
    }
}
