package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Templates")
@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Operation(summary = "Add a certificate template")
    @PostMapping
    public ResponseEntity<CertificateTemplate> add(@RequestBody CertificateTemplate template) {
        return ResponseEntity.ok(templateService.addTemplate(template));
    }

    @Operation(summary = "List all templates")
    @GetMapping
    public ResponseEntity<List<CertificateTemplate>> list() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
}
