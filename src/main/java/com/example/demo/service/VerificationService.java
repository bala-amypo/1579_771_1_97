package com.example.demo.service;

import com.example.demo.dto.VerificationLogDTO;
import java.util.List;

public interface VerificationLogService {
    List<VerificationLogDTO> getAll();
    VerificationLogDTO create(VerificationLogDTO dto);
}
