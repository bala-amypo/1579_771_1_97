package com.example.demo.service.impl;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService 
{
    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;
    @Override
    public Certificate generateCertificate(Long studentId, Long templateId) 
    {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found")); [cite: 267]
        CertificateTemplate template = templateRepository.findById(templateId)
            .orElseThrow(() -> new ResourceNotFoundException("Template not found")); [cite: 268]
        String vCode = "VC-" + UUID.randomUUID().toString().toUpperCase().substring(0, 8); [cite: 272]
        Certificate certificate = Certificate.builder()
            .student(student)
            .template(template)
            .issuedDate(LocalDate.now()) [cite: 271]
            .verificationCode(vCode)
            .qrCodeUrl("data:image/png;base64," + vCode) 
            .build();
        return certificateRepository.save(certificate);
    }
    @Override
    public Certificate getCertificate(Long certificateId) 
    {
        return certificateRepository.findById(certificateId)
            .orElseThrow(() -> new ResourceNotFoundException("Certificate not found")); [cite: 277]
    }

    @Override
    public Certificate findByVerificationCode(String code) 
    {
        return certificateRepository.findByVerificationCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Certificate not found")); [cite: 280]
    }

    @Override
    public List<Certificate> findByStudentId(Long studentId) 
    {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found")); [cite: 283]
        return certificateRepository.findByStudent(student);
    }
}