import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.net.URL;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email; 
    @Column(unique = true, nullable = false)
    private String rollNumber;
}
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CertificateTemplate 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String templateName;
    private String backgroundUrl;
    private String fontStyle;
    private String signatureName;
}
@Repository
interface StudentRepository extends JpaRepository<Student, Long> {}
@Repository
interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {}
@Service
@RequiredArgsConstructor
class AcademicService 
{

    private final StudentRepository studentRepository;
    private final CertificateTemplateRepository templateRepository;
    public Student saveStudent(Student student) 
    {
        return studentRepository.save(student);
    }
    public CertificateTemplate saveTemplate(CertificateTemplate template) 
    {
        if (!isValidUrl(template.getBackgroundUrl()))
        {
            throw new IllegalArgumentException("Invalid Background URL");
        }
        return templateRepository.save(template);
    }
    private boolean isValidUrl(String urlString) 
    {
        try 
        {
            new URL(urlString).toURI();
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
}