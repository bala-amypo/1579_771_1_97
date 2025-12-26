package com.example.demo.service.impl;

import com.example.demo.dto.CertificateTemplateDTO;
import com.example.demo.entity.CertificateTemplate;
import com.example.demo.repository.CertificateTemplateRepository;
import com.example.demo.service.CertificateTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateTemplateServiceImpl implements CertificateTemplateService {

    private final CertificateTemplateRepository repository;

    public CertificateTemplateServiceImpl(CertificateTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CertificateTemplateDTO> getAll() {
        return repository.findAll().stream()
                .map(t -> new CertificateTemplateDTO(
                        t.getId(), t.getTemplateName(), t.getBackgroundUrl(), t.getFontStyle(), t.getSignatureName()))
                .collect(Collectors.toList());
    }

    @Override
    public CertificateTemplateDTO create(CertificateTemplateDTO dto) {
        CertificateTemplate t = new CertificateTemplate();
        t.setTemplateName(dto.getTemplateName());
        t.setBackgroundUrl(dto.getBackgroundUrl());
        t.setFontStyle(dto.getFontStyle());
        t.setSignatureName(dto.getSignatureName());

        CertificateTemplate saved = repository.save(t);
        return new CertificateTemplateDTO(
                saved.getId(), saved.getTemplateName(), saved.getBackgroundUrl(), saved.getFontStyle(), saved.getSignatureName());
    }
}
