package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "certificate_templates")
public class CertificateTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String templateName;
    private String backgroundUrl;

    // Getters/Setters
    public Long getId() { return id; }
    public String getTemplateName() { return templateName; }
    public String getBackgroundUrl() { return backgroundUrl; }
    public void setId(Long id) { this.id = id; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
    public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final CertificateTemplate t = new CertificateTemplate();
        public Builder id(Long id) { t.id = id; return this; }
        public Builder templateName(String name) { t.templateName = name; return this; }
        public Builder backgroundUrl(String url) { t.backgroundUrl = url; return this; }
        public CertificateTemplate build() { return t; }
    }
}
