// src/main/java/com/example/demo/service/impl/CertificateServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CertificateService;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

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
      .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    CertificateTemplate template = templateRepository.findById(templateId)
      .orElseThrow(() -> new ResourceNotFoundException("Template not found"));

    String verificationCode = "VC-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
    String qrPayload = "VERIFY:" + verificationCode;
    String base64Png = Base64.getEncoder().encodeToString(qrPayload.getBytes(StandardCharsets.UTF_8));
    String dataUrl = "data:image/png;base64," + base64Png;

    Certificate cert = Certificate.builder()
      .student(student)
      .template(template)
      .issuedDate(LocalDate.now())
      .verificationCode(verificationCode)
      .qrCodeUrl(dataUrl)
      .build();

    return certificateRepository.save(cert);
  }

  @Override
  public Certificate getCertificate(Long certificateId) {
    return certificateRepository.findById(certificateId)
      .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
  }

  @Override
  public Certificate findByVerificationCode(String code) {
    return certificateRepository.findByVerificationCode(code)
      .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
  }

  @Override
  public List<Certificate> findByStudentId(Long studentId) {
    Student student = studentRepository.findById(studentId)
      .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    return certificateRepository.findByStudent(student);
  }
}
