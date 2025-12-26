package com.example.demo.service.impl;

import com.example.demo.dto.CertificateDTO;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.entity.Student;
import com.example.demo.repository.CertificateRepository;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CertificateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<CertificateDTO> getAll() {
        return certificateRepository.findAll().stream()
                .map(c -> new CertificateDTO(
                        c.getId(),
                        c.getStudent().getId(),
                        c.getTemplate().getId(),
                        c.getIssuedDate(),
                        c.getQrCodeUrl(),
                        c.getVerificationCode()))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateDTO create(CertificateDTO dto) {
        Optional<Student> studentOpt = studentRepository.findById(dto.getStudentId());
        Optional<CertificateTemplate> templateOpt = templateRepository.findById(dto.getTemplateId());
        if (studentOpt.isEmpty() || templateOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid studentId or templateId");
        }

        Certificate c = new Certificate();
        c.setStudent(studentOpt.get());
        c.setTemplate(templateOpt.get());
        c.setIssuedDate(dto.getIssuedDate());
        c.setQrCodeUrl(dto.getQrCodeUrl());
        c.setVerificationCode(dto.getVerificationCode());

        Certificate saved = certificateRepository.save(c);
        return new CertificateDTO(
                saved.getId(),
                saved.getStudent().getId(),
                saved.getTemplate().getId(),
                saved.getIssuedDate(),
                saved.getQrCodeUrl(),
                saved.getVerificationCode());
    }
}
