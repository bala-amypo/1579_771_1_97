package com.example.demo.controller;

import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping
    public ResponseEntity<VerificationLog> verifyCertificate(@RequestParam String code,
                                                             @RequestParam String ip) {
        return ResponseEntity.ok(verificationService.verifyCertificate(code, ip));
    }

    @GetMapping("/{certificateId}/logs")
    public ResponseEntity<List<VerificationLog>> getLogsByCertificate(@PathVariable Long certificateId) {
        return ResponseEntity.ok(verificationService.getLogsByCertificate(certificateId));
    }
}
