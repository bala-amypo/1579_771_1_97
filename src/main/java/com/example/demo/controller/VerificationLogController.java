// src/main/java/com/example/demo/controller/VerificationController.java
package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<VerificationLog> verify(@PathVariable String verificationCode, HttpServletRequest request) {
    String ip = request.getRemoteAddr();
    return ResponseEntity.ok(verificationService.verifyCertificate(verificationCode, ip));
  }

  @GetMapping("/logs/{certificateId}")
  public ResponseEntity<List<VerificationLog>> logs(@PathVariable Long certificateId) {
    return ResponseEntity.ok(verificationService.getLogsByCertificate(certificateId));
  }
}
