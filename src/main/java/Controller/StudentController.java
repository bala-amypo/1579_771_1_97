package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students") [cite: 322]
@RequiredArgsConstructor
public class StudentController
{
    private final StudentService studentService;

    @PostMapping [cite: 325]
    public Student addStudent(@RequestBody Student student) 
    {
        return studentService.addStudent(student); [cite: 327]
    }

    @GetMapping [cite: 328]
    public List<Student> getAllStudents() 
    {
        return studentService.getAllStudents(); [cite: 330]
    }
}