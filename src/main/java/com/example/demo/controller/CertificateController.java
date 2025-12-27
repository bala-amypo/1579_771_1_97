package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Certificates")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @Operation(summary = "Generate a certificate for student using template")
    @PostMapping("/generate/{studentId}/{templateId}")
    public ResponseEntity<Certificate> generate(@PathVariable Long studentId, @PathVariable Long templateId) {
        return ResponseEntity.ok(certificateService.generateCertificate(studentId, templateId));
    }

    @Operation(summary = "Get certificate by id")
    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> get(@PathVariable Long certificateId) {
        return ResponseEntity.ok(certificateService.getCertificate(certificateId));
    }

    @Operation(summary = "Find certificate by verification code")
    @GetMapping("/verify/code/{verificationCode}")
    public ResponseEntity<Certificate> findByCode(@PathVariable String verificationCode) {
        return ResponseEntity.ok(certificateService.findByVerificationCode(verificationCode));
    }

    @Operation(summary = "List all certificates for a student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Certificate>> findByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(certificateService.findByStudentId(studentId));
    }
}
