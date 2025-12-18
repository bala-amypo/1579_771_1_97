@Service
public class VerificationService 
{
    @Autowired
    private VerificationLogRepository verificationLogRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) 
    {
        Certificate cert = certificateRepository.findByVerificationCode(verificationCode);
        VerificationLog log = new VerificationLog();
        log.setVerificationCode(verificationCode);
        log.setClientIp(clientIp);
        log.setVerifiedAt(java.time.LocalDateTime.now());
        log.setValid(cert != null);
        return verificationLogRepository.save(log);
    }
    public List<VerificationLog> getLogsByCertificate(Long certificateId) 
    {
        return verificationLogRepository.findByCertificateId(certificateId);
    }
}