package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 46]
    private String name; [cite: 47]
    @Column(unique = true)
    private String email; [cite: 48]
    @Column(unique = true)
    private String rollNumber; [cite: 49]
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Certificate> certificates; [cite: 54]
}