package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentDTO dto) {
        return service.create(dto);
    }
}
