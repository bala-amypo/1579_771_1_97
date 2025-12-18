@Repository
public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> 
{
    List<VerificationLog> findByCertificateId(Long certificateId);
}