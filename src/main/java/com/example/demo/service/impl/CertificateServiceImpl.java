package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

  private final CertificateRepository certificateRepository;
  private final StudentRepository studentRepository;
  private final CertificateTemplateRepository templateRepository;

  public CertificateServiceImpl(CertificateRepository certificateRepository,
                                StudentRepository studentRepository,
                                CertificateTemplateRepository templateRepository) {
    this.certificateRepository = certificateRepository;
    this.studentRepository = studentRepository;
    this.templateRepository = templateRepository;
  }

  @Override
  public Certificate generateCertificate(Long studentId, Long templateId) {
    Student student = studentRepository.findById(studentId)
      .orElseThrow(() -> new RuntimeException("Student not found"));
    CertificateTemplate template = templateRepository.findById(templateId)
      .orElseThrow(() -> new RuntimeException("Template not found"));

    String verificationCode = "VC-" + UUID.randomUUID();
    String dummyPng = Base64.getEncoder().encodeToString(("QR:" + verificationCode).getBytes());
    String qrDataUrl = "data:image/png;base64," + dummyPng;

    Certificate cert = Certificate.builder()
      .student(student)
      .template(template)
      .verificationCode(verificationCode)
      .qrCodeUrl(qrDataUrl)
      .issuedDate(LocalDate.now())
      .build();

    return certificateRepository.save(cert);
  }

  @Override
  public Certificate getCertificate(Long certificateId) {
    return certificateRepository.findById(certificateId)
      .orElseThrow(() -> new RuntimeException("Certificate not found"));
  }

  @Override
  public Certificate findByVerificationCode(String code) {
    Optional<Certificate> found = certificateRepository.findByVerificationCode(code);
    return found.orElseThrow(() -> new RuntimeException("Certificate not found"));
  }

  @Override
  public List<Certificate> findByStudentId(Long studentId) {
    Student s = studentRepository.findById(studentId)
      .orElseThrow(() -> new RuntimeException("Student not found"));
    return certificateRepository.findByStudent(s);
  }
}
