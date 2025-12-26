package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  @Operation(summary = "Add student")
  public Student add(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

  @GetMapping
  @Operation(summary = "List students")
  public List<Student> list() {
    return studentService.getAllStudents();
  }
}
