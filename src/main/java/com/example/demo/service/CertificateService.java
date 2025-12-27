package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;

public interface CertificateService {
    Certificate generateCertificate(Student student, CertificateTemplate template, String code, String qrUrl);

    // Overloads for controller convenience
    Certificate generateCertificate(Long studentId, Long templateId);

    Certificate getCertificate(Long id);

    Certificate findByVerificationCode(String code);
}
