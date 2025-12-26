package com.example.demo.controller;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  @Operation(summary = "Add template")
  public CertificateTemplate add(@RequestBody CertificateTemplate template) {
    return templateService.addTemplate(template);
  }

  @GetMapping
  @Operation(summary = "List templates")
  public List<CertificateTemplate> list() {
    return templateService.getAllTemplates();
  }
}
