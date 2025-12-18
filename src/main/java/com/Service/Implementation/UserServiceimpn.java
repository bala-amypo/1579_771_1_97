@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;
    public User register(User user) 
    {
        return userRepository.save(user);
    }
    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}