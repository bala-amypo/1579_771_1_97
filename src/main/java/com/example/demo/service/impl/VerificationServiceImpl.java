package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final CertificateRepository certificateRepository;
    private final VerificationLogRepository logRepository;

    public VerificationServiceImpl(CertificateRepository certificateRepository,
                                   VerificationLogRepository logRepository) {
        this.certificateRepository = certificateRepository;
        this.logRepository = logRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        Certificate cert = certificateRepository.findByVerificationCode(verificationCode)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        VerificationLog log = VerificationLog.builder()
                .certificate(cert)
                .verifiedAt(LocalDateTime.now())
                .status("SUCCESS")
                .ipAddress(clientIp)
                .build();

        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        return logRepository.findByCertificateId(certificateId);
    }
}
