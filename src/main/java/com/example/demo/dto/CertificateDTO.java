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
}
