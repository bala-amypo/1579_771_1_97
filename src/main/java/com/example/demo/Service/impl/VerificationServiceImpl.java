package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final VerificationLogRepository logRepository;
    private final CertificateRepository certificateRepository;

    public VerificationServiceImpl(VerificationLogRepository logRepository,
                                   CertificateRepository certificateRepository) {
        this.logRepository = logRepository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) {
        VerificationLog.VerificationLogBuilder builder = VerificationLog.builder()
                .verifiedAt(LocalDateTime.now())
                .ipAddress(clientIp);

        Certificate certificate = certificateRepository.findByVerificationCode(verificationCode).orElse(null);
        if (certificate != null) {
            builder.certificate(certificate).status("SUCCESS");
        } else {
            builder.status("FAILED");
        }

        VerificationLog log = builder.build();
        return logRepository.save(log);
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
        return certificate.getLogs() != null ? certificate.getLogs() : logRepository.findAll()
                .stream()
                .filter(l -> l.getCertificate() != null && l.getCertificate().getId().equals(certificateId))
                .toList();
    }
}
