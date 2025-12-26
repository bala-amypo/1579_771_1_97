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
}
