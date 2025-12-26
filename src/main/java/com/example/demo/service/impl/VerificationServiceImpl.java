package com.example.demo.service.impl;

import com.example.demo.dto.VerificationLogDTO;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.VerificationLogRepository;
import com.example.demo.service.VerificationLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    private final VerificationLogRepository repository;
    private final CertificateRepository certificateRepository;

    public VerificationLogServiceImpl(VerificationLogRepository repository,
                                      CertificateRepository certificateRepository) {
        this.repository = repository;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<VerificationLogDTO> getAll() {
        return repository.findAll().stream()
                .map(v -> new VerificationLogDTO(
                        v.getId(),
                        v.getCertificate().getId(),
                        v.getVerifiedAt(),
                        v.getStatus(),
                        v.getIpAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public VerificationLogDTO create(VerificationLogDTO dto) {
        Optional<Certificate> certOpt = certificateRepository.findById(dto.getCertificateId());
        if (certOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid certificateId");
        }

        VerificationLog v = new VerificationLog();
        v.setCertificate(certOpt.get());
        v.setVerifiedAt(dto.getVerifiedAt());
        v.setStatus(dto.getStatus());
        v.setIpAddress(dto.getIpAddress());

        VerificationLog saved = repository.save(v);
        return new VerificationLogDTO(
                saved.getId(),
                saved.getCertificate().getId(),
                saved.getVerifiedAt(),
                saved.getStatus(),
                saved.getIpAddress());
    }
}
