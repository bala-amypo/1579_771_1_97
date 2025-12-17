import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Entity
@Table(name = "users")
@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @Builder
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @PrePersist
    public void setDefaultRole() 
    {
        if (this.role == null || this.role.isEmpty()) 
        {
            this.role = "STAFF";
        }
    }
}
@Repository
interface UserRepository extends JpaRepository<User, Long> 
{
    boolean existsByEmail(String email);
}
@Service
@RequiredArgsConstructor
class UserService 
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Transactional
    public User createUser(String name, String email, String rawPassword, String role)
     {
        if (userRepository.existsByEmail(email))
        {
            throw new RuntimeException("Email already in use!");
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(rawPassword)) 
                .role(role) 
                .build();
        return userRepository.save(user);
    }
}