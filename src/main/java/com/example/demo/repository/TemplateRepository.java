// src/main/java/com/example/demo/repository/CertificateTemplateRepository.java
package com.example.demo.repository;

import com.example.demo.entity.CertificateTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {
  Optional<CertificateTemplate> findByTemplateName(String templateName);
}
