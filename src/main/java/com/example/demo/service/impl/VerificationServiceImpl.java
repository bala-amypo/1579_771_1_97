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
import java.util.Optional;

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
        Optional<Certificate> found = certificateRepository.findByVerificationCode(verificationCode);

        VerificationLog log = VerificationLog.builder()
                .certificate(found.orElse(null))
                .verifiedAt(LocalDateTime.now())
                .status(found.isPresent() ? "SUCCESS" : "FAILED")
                .ipAddress(clientIp)
                .build();

        logRepository.save(log);

        if (found.isEmpty()) {
            throw new ResourceNotFoundException("Certificate not found");
        }
        return log;
    }

    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) {
        Certificate cert = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
        // For simplicity, return all logs and let clients filter; or add a query if needed
        return logRepository.findAll().stream()
                .filter(l -> l.getCertificate() != null && l.getCertificate().getId().equals(cert.getId()))
                .toList();
    }
}
