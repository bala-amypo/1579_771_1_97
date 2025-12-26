package com.example.certificates.controller;

import com.example.certificates.dto.CertificateDTO;
import com.example.certificates.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService service;

    public CertificateController(CertificateService service) {
        this.service = service;
    }

    @GetMapping
    public List<CertificateDTO> getCertificates() {
        return service.getAllCertificates();
    }

    @PostMapping
    public CertificateDTO createCertificate(@RequestBody CertificateDTO dto) {
        return service.createCertificate(dto);
    }
}
