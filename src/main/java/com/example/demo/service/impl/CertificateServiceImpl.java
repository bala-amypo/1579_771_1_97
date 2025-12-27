package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CertificateServiceImpl {

    private final CertificateRepository certificateRepository;
    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public Certificate generateCertificate(Student student, CertificateTemplate template, String code, String qrUrl) {
        Certificate cert = Certificate.builder()
                .student(student)
                .template(template)
                .verificationCode(code)
                .issuedDate(LocalDate.now())
                .qrCodeUrl(qrUrl)
                .build();
        return certificateRepository.save(cert);
    }
}
