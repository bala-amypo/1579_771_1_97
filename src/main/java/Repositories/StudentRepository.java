@Repository
public interface StudentRepository extends JpaRepository<Student, Long> 
{
    boolean existsByEmail(String email);
}