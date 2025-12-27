package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.VerificationLog;

public interface VerificationService {
    VerificationLog verifyCertificate(Certificate cert, boolean success, String ip);
}
