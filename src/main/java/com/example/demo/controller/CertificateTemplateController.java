// src/main/java/com/example/demo/controller/TemplateController.java
package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@Tag(name = "Templates")
public class TemplateController {

  private final TemplateService templateService;

  public TemplateController(TemplateService templateService) {
    this.templateService = templateService;
  }

  @PostMapping
  public ResponseEntity<CertificateTemplate> add(@RequestBody CertificateTemplate template) {
    return ResponseEntity.ok(templateService.addTemplate(template));
  }

  @GetMapping
  public ResponseEntity<List<CertificateTemplate>> list() {
    return ResponseEntity.ok(templateService.getAllTemplates());
  }
}
