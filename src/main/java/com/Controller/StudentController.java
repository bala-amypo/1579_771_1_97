@RestController
@RequestMapping("/students")
public class StudentController 
{
    @Autowired
    private StudentService studentService;
    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) 
    {
        return ResponseEntity.ok(studentService.addStudent(student));
    }
    @GetMapping("/")
    public ResponseEntity<List<Student>> listAllStudents() 
    {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}