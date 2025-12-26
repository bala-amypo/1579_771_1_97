package com.example.demo.dto;

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String rollNumber;

    public StudentDTO() {}
    public StudentDTO(Long id, String name, String email, String rollNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rollNumber = rollNumber;
    }

    // Getters and Setters
}
