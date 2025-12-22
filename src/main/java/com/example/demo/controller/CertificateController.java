package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/generate/{studentId}/{templateId}")
    public ResponseEntity<Certificate> generate(@PathVariable Long studentId, @PathVariable Long templateId) {
        return ResponseEntity.ok(certificateService.generateCertificate(studentId, templateId));
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> get(@PathVariable Long certificateId) {
        return ResponseEntity.ok(certificateService.getCertificate(certificateId));
    }

    @GetMapping("/verify/code/{verificationCode}")
    public ResponseEntity<Certificate> byCode(@PathVariable String verificationCode) {
        return ResponseEntity.ok(certificateService.findByVerificationCode(verificationCode));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Certificate>> byStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(certificateService.findByStudentId(studentId));
    }
}
