package com.example.demo.service;

import com.example.demo.dto.CertificateDTO;
import java.util.List;

public interface CertificateService {
    List<CertificateDTO> getAll();
    CertificateDTO create(CertificateDTO dto);
}
