package com.example.demo.controller;

import com.example.demo.dto.CertificateDTO;
import com.example.demo.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService service;

    public CertificateController(CertificateService service) { this.service = service; }

    @GetMapping
    public List<CertificateDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CertificateDTO create(@RequestBody CertificateDTO dto) {
        return service.create(dto);
    }
}
