@Repository
public interface UserRepository extends JpaRepository<User, Long> 
{
    User findByEmail(String email);
}