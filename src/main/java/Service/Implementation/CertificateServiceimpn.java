@Service
public class CertificateService 
{
    @Autowired
    private CertificateRepository certificateRepository;
    public Certificate generateCertificate(Long studentId, Long templateId) 
    {
        Certificate certificate = new Certificate();
        certificate.setStudentId(studentId);
        certificate.setTemplateId(templateId);
        certificate.setVerificationCode(java.util.UUID.randomUUID().toString());
        return certificateRepository.save(certificate);
    }
    public Certificate getCertificate(Long certificateId) 
    {
        return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }
    public Certificate findByVerificationCode(String code) 
    {
        return certificateRepository.findByVerificationCode(code);
    }
    public List<Certificate> findByStudentId(Long studentId) 
    {
        return certificateRepository.findByStudentId(studentId);
    }
}