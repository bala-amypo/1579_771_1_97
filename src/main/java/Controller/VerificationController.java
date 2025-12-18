package com.example.demo.controller;
import com.example.demo.entity.VerificationLog;
import com.example.demo.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/verify") [cite: 355]
@RequiredArgsConstructor
public class VerificationController 
{
    private final VerificationService verificationService;
    @PostMapping("/{verificationCode}") [cite: 358]
    public VerificationLog verify(@PathVariable String verificationCode, HttpServletRequest request) 
    {
        String clientIp = request.getRemoteAddr(); [cite: 360]
        return verificationService.verifyCertificate(verificationCode, clientIp);
    }
    @GetMapping("/logs/{certificateId}") [cite: 361]
    public List<VerificationLog> getLogs(@PathVariable Long certificateId) 
    {
        return verificationService.getLogsByCertificate(certificateId); [cite: 363]
    }
}