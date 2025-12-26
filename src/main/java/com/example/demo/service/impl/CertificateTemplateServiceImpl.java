// src/main/java/com/example/demo/service/impl/TemplateServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.CertificateTemplate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.TemplateService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TemplateServiceImpl implements TemplateService {

  private final CertificateTemplateRepository templateRepository;

  public TemplateServiceImpl(CertificateTemplateRepository templateRepository) {
    this.templateRepository = templateRepository;
  }

  @Override
  public CertificateTemplate addTemplate(CertificateTemplate template) {
    if (templateRepository.findByTemplateName(template.getTemplateName()).isPresent()) {
      throw new RuntimeException("Template name exists");
    }
    validateUrl(template.getBackgroundUrl());
    return templateRepository.save(template);
  }

  private void validateUrl(String url) {
    try {
      new URL(url);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Invalid template backgroundUrl");
    }
  }

  @Override
  public List<CertificateTemplate> getAllTemplates() {
    return templateRepository.findAll();
  }

  @Override
  public CertificateTemplate findById(Long id) {
    return templateRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Template not found"));
  }
}
