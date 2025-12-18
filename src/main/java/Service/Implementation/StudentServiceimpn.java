package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService 
{
    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) 
    {
        if (studentRepository.findByEmail(student.getEmail()).isPresent() || 
            studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) 
        {
            throw new RuntimeException("Student email exists"); [cite: 57, 236]
        }
        return studentRepository.save(student); [cite: 237]
    }

    @Override
    public List<Student> getAllStudents() 
    {
        return studentRepository.findAll(); [cite: 239]
    }

    @Override
    public Student findById(Long id) 
    {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found")); [cite: 58, 242]
    }
}