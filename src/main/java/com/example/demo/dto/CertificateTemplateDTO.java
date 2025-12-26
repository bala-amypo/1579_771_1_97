package com.example.demo.dto;

public class CertificateTemplateDTO {
    private Long id;
    private String templateName;
    private String backgroundUrl;
    private String fontStyle;
    private String signatureName;

    public CertificateTemplateDTO() {}
    public CertificateTemplateDTO(Long id, String templateName, String backgroundUrl, String fontStyle, String signatureName) {
        this.id = id;
        this.templateName = templateName;
        this.backgroundUrl = backgroundUrl;
        this.fontStyle = fontStyle;
        this.signatureName = signatureName;
    }

    // Getters and Setters
}
