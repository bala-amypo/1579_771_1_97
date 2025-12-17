import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

public interface UserService 
{
    User register(User user);
    User findByEmail(String email);
}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService 
{
    private final UserRepository userRepository;

    @Override
    public User register(User user) 
    {
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) 
    {
        return userRepository.findByEmail(email);
    }
}