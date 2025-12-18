@RestController
@RequestMapping("/auth")
public class AuthController 
{
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) 
    {
        return ResponseEntity.ok(userService.register(user));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) 
    {
        return ResponseEntity.ok("Login successful");
    }
}