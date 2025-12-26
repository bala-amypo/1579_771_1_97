// src/main/java/com/example/demo/service/impl/VerificationServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    Optional<Certificate> certOpt = certificateRepository.findByVerificationCode(verificationCode);
    VerificationLog log = VerificationLog.builder()
      .certificate(certOpt.orElse(null))
      .verifiedAt(LocalDateTime.now())
      .status(certOpt.isPresent() ? "SUCCESS" : "FAILED")
      .ipAddress(clientIp)
      .build();
    return logRepository.save(log);
  }

  @Override
  public List<VerificationLog> getLogsByCertificate(Long certificateId) {
    Certificate cert = certificateRepository.findById(certificateId)
      .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    // Simple approach: fetch all and filter (could be optimized with a query)
    return logRepository.findAll().stream()
      .filter(l -> l.getCertificate() != null && cert.getId().equals(l.getCertificate().getId()))
      .toList();
  }
}
