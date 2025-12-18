@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> 
{
    Optional<Certificate> findByVerificationCode(String code);
    List<Certificate> findByStudentId(Long studentId);
}