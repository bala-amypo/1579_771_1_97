package com.example.demo.service.impl;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentDTO> getAll() {
        return repository.findAll().stream()
                .map(s -> new StudentDTO(s.getId(), s.getName(), s.getEmail(), s.getRollNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO create(StudentDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setRollNumber(dto.getRollNumber());

        Student saved = repository.save(s);
        return new StudentDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.getRollNumber());
    }
}
