package com.example.certificates.service;

import com.example.certificates.dto.CertificateDTO;
import com.example.certificates.entity.Certificate;
import com.example.certificates.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateService {

    private final CertificateRepository repository;

    public CertificateService(CertificateRepository repository) {
        this.repository = repository;
    }

    public List<CertificateDTO> getAllCertificates() {
        return repository.findAll().stream()
                .map(c -> new CertificateDTO(c.getId(), c.getStudentName(), c.getCourseName(), c.getIssueDate()))
                .collect(Collectors.toList());
    }

    public CertificateDTO createCertificate(CertificateDTO dto) {
        Certificate certificate = new Certificate();
        certificate.setStudentName(dto.getStudentName());
        certificate.setCourseName(dto.getCourseName());
        certificate.setIssueDate(dto.getIssueDate());

        Certificate saved = repository.save(certificate);
        return new CertificateDTO(saved.getId(), saved.getStudentName(), saved.getCourseName(), saved.getIssueDate());
    }
}
