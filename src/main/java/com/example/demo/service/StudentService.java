package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    List<StudentDTO> getAll();
    StudentDTO create(StudentDTO dto);
}
