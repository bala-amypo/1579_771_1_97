@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> 
{
    boolean existsByName(String name);
}