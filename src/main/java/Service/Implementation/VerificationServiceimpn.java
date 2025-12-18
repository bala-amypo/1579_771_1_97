package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService 
{
    private final VerificationLogRepository logRepository;
    private final CertificateRepository certificateRepository;
    @Override
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) 
    {
        var certOpt = certificateRepository.findByVerificationCode(verificationCode);
        String status = certOpt.isPresent() ? "SUCCESS" : "FAILED"; [cite: 292]

        VerificationLog log = VerificationLog.builder()
            .certificate(certOpt.orElse(null))
            .verifiedAt(LocalDateTime.now()) [cite: 295]
            .status(status)
            .ipAddress(clientIp) [cite: 297]
            .build();

        return logRepository.save(log);
    }
    @Override
    public List<VerificationLog> getLogsByCertificate(Long certificateId) 
    {
        Certificate certificate = certificateRepository.findById(certificateId)
            .orElseThrow(() -> new RuntimeException("Certificate not found")); [cite: 300]
        return logRepository.findAll().stream()
            .filter(l -> l.getCertificate() != null && l.getCertificate().getId().equals(certificateId))
            .toList();
    }
}