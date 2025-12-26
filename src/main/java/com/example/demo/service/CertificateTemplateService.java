package com.example.demo.service;

import com.example.demo.dto.CertificateTemplateDTO;
import java.util.List;

public interface CertificateTemplateService {
    List<CertificateTemplateDTO> getAll();
    CertificateTemplateDTO create(CertificateTemplateDTO dto);
}
