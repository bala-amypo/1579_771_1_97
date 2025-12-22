package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/{verificationCode}")
    public ResponseEntity<VerificationLog> verify(@PathVariable String verificationCode,
                                                  HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        return ResponseEntity.ok(verificationService.verifyCertificate(verificationCode, clientIp));
    }

    @GetMapping("/logs/{certificateId}")
    public ResponseEntity<List<VerificationLog>> logs(@PathVariable Long certificateId) {
        return ResponseEntity.ok(verificationService.getLogsByCertificate(certificateId));
    }
}
