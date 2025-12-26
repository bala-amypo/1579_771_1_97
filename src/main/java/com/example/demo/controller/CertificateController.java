package com.example.demo.controller;

import com.example.demo.entity.Certificate;
import com.example.demo.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificates")
@Tag(name = "Certificates")
public class CertificateController {

  private final CertificateService certificateService;

  public CertificateController(CertificateService certificateService) {
    this.certificateService = certificateService;
  }

  @PostMapping("/generate/{studentId}/{templateId}")
  @Operation(summary = "Generate certificate")
  public Certificate generate(@PathVariable Long studentId, @PathVariable Long templateId) {
    return certificateService.generateCertificate(studentId, templateId);
  }

  @GetMapping("/{certificateId}")
  @Operation(summary = "Get certificate by ID")
  public Certificate get(@PathVariable Long certificateId) {
    return certificateService.getCertificate(certificateId);
  }

  @GetMapping("/verify/code/{verificationCode}")
  @Operation(summary = "Find certificate by verification code")
  public Certificate findByCode(@PathVariable String verificationCode) {
    return certificateService.findByVerificationCode(verificationCode);
  }
}
