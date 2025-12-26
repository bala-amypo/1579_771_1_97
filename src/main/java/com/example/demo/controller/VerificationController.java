package com.example.demo.controller;

import com.example.demo.dto.VerificationLogDTO;
import com.example.demo.service.VerificationLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verification-logs")
public class VerificationLogController {

    private final VerificationLogService service;

    public VerificationLogController(VerificationLogService service) { this.service = service; }

    @GetMapping
    public List<VerificationLogDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public VerificationLogDTO create(@RequestBody VerificationLogDTO dto) {
        return service.create(dto);
    }
}
