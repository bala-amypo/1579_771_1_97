package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(VerificationLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog verifyCertificate(Certificate cert, boolean success, String ip) {
        VerificationLog log = VerificationLog.builder()
                .certificate(cert)
                .status(success ? "SUCCESS" : "FAILED")
                .verifiedAt(LocalDateTime.now())
                .ipAddress(ip)
                .build();
        return logRepository.save(log);
    }
}
