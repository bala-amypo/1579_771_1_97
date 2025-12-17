import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Entity
@Table(name = "students")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true) 
    private String email;
    @Column(nullable = false, unique = true) 
    private String rollNumber;
}
@Repository
interface StudentRepository extends JpaRepository<Student, Long> 
{
    boolean existsByEmail(String email);
    boolean existsByRollNumber(String rollNumber);
}
@Service
@RequiredArgsConstructor
class StudentService 
{

    private final StudentRepository studentRepository;
    @Transactional
    public Student registerStudent(String name, String email, String rollNumber) 
    {
        if (studentRepository.existsByEmail(email)) 
        {
            throw new RuntimeException("Error: Email is already registered!");
        }
        if (studentRepository.existsByRollNumber(rollNumber))
        {
            throw new RuntimeException("Error: Roll number is already assigned!");
        }

        Student student = Student.builder()
                .name(name)
                .email(email)
                .rollNumber(rollNumber)
                .build();

        return studentRepository.save(student);
    }
}