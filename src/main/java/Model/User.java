package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 28]
    private String name; [cite: 29]
    @Column(unique = true)
    private String email; [cite: 30]
    private String password; [cite: 31]
    private String role; 
}