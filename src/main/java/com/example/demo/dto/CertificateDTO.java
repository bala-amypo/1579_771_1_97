package com.example.demo.dto;

import java.time.LocalDate;

public class CertificateDTO {
    private Long id;
    private Long studentId;
    private Long templateId;
    private LocalDate issuedDate;
    private String qrCodeUrl;
    private String verificationCode;

    public CertificateDTO() {}
    public CertificateDTO(Long id, Long studentId, Long templateId, LocalDate issuedDate,
                          String qrCodeUrl, String verificationCode) {
        this.id = id;
        this.studentId = studentId;
        this.templateId = templateId;
        this.issuedDate = issuedDate;
        this.qrCodeUrl = qrCodeUrl;
        this.verificationCode = verificationCode;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public String getQrCodeUrl() { return qrCodeUrl; }
    public void setQrCodeUrl(String qrCodeUrl) { this.qrCodeUrl = qrCodeUrl; }

    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
}
