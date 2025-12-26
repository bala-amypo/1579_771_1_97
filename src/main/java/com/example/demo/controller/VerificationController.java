package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verify")
@Tag(name = "Verification")
public class VerificationController {

  private final VerificationService verificationService;

  public VerificationController(VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  @PostMapping("/{verificationCode}")
  @Operation(summary = "Verify certificate and record log")
  public VerificationLog verify(@PathVariable String verificationCode,
                                @RequestHeader(value = "X-Client-IP", required = false) String clientIp) {
    String ip = (clientIp == null || clientIp.isBlank()) ? "0.0.0.0" : clientIp;
    return verificationService.verifyCertificate(verificationCode, ip);
  }

  @GetMapping("/logs/{certificateId}")
  @Operation(summary = "Get verification logs for certificate")
  public List<VerificationLog> getLogs(@PathVariable Long certificateId) {
    return verificationService.getLogsByCertificate(certificateId);
  }
}
