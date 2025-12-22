package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String rollNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    public Student() {}

    public Student(Long id, String name, String email, String rollNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rollNumber = rollNumber;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public List<Certificate> getCertificates() { return certificates; }
    public void setCertificates(List<Certificate> certificates) { this.certificates = certificates; }
}
