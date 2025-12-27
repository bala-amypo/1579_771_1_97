package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String rollNumber;

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRollNumber() { return rollNumber; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Student s = new Student();
        public Builder id(Long id) { s.id = id; return this; }
        public Builder name(String name) { s.name = name; return this; }
        public Builder email(String email) { s.email = email; return this; }
        public Builder rollNumber(String rollNumber) { s.rollNumber = rollNumber; return this; }
        public Student build() { return s; }
    }
}
