package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "verification_logs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationLog 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 107]
    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate; [cite: 108, 114]
    private LocalDateTime verifiedAt; [cite: 109]
    private String status; [cite: 110]
    private String ipAddress; [cite: 111]
}