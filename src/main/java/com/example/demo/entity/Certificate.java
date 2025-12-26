package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String courseName;
    private String issueDate;

    // Getters and Setters
}
