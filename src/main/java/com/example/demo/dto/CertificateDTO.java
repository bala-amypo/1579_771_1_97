package com.example.demo.dto;

public class CertificateDTO {
    private Long id;
    private String studentName;
    private String courseName;
    private String issueDate;

    // Default constructor
    public CertificateDTO() {}

    // All-args constructor
    public CertificateDTO(Long id, String studentName, String courseName, String issueDate) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.issueDate = issueDate;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
}
