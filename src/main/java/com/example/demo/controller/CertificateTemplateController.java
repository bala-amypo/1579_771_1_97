package com.example.demo.controller;

import com.example.demo.dto.CertificateTemplateDTO;
import com.example.demo.service.CertificateTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class CertificateTemplateController {

    private final CertificateTemplateService service;

    public CertificateTemplateController(CertificateTemplateService service) { this.service = service; }

    @GetMapping
    public List<CertificateTemplateDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public CertificateTemplateDTO create(@RequestBody CertificateTemplateDTO dto) {
        return service.create(dto);
    }
}
