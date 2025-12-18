@Service
public class StudentService 
{
    @Autowired
    private StudentRepository studentRepository;
    public Student addStudent(Student student) 
    {
        if (studentRepository.existsByEmail(student.getEmail())) 
        {
            throw new RuntimeException("Student email exists");
        }
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents() 
    {
        return studentRepository.findAll();
    }
    public Student findById(Long id) 
    {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}