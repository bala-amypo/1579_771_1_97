import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
public interface StudentService 
{
    Student addStudent(Student student);
    List<Student> getAllStudents();
    Student findById(Long id);
}
@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService 
{
    private final StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) 
    {
        if (studentRepository.existsByEmail(student.getEmail())) 
        {
            throw new RuntimeException("Student email exists");
        }
        return studentRepository.save(student);
    }
    @Override
    public List<Student> getAllStudents() 
    {
        return studentRepository.findAll();
    }
    @Override
    public Student findById(Long id)  
    {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}