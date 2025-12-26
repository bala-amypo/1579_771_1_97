package com.example.demo.dto;

import java.time.LocalDateTime;

public class VerificationLogDTO {
    private Long id;
    private Long certificateId;
    private LocalDateTime verifiedAt;
    private String status;
    private String ipAddress;

    public VerificationLogDTO() {}
    public VerificationLogDTO(Long id, Long certificateId, LocalDateTime verifiedAt, String status, String ipAddress) {
        this.id = id;
        this.certificateId = certificateId;
        this.verifiedAt = verifiedAt;
        this.status = status;
        this.ipAddress = ipAddress;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCertificateId() { return certificateId; }
    public void setCertificateId(Long certificateId) { this.certificateId = certificateId; }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}
