@Service
public class VerificationService 
{
    @Autowired
    private VerificationLogRepository logRepository;
    @Autowired
    private CertificateService certificateService;
    public VerificationLog verifyCertificate(String verificationCode, String clientIp) 
    {
        Certificate cert = certificateService.findByVerificationCode(verificationCode);
        VerificationLog log = new VerificationLog();
        log.setClientIp(clientIp);
        log.setVerificationTime(LocalDateTime.now());
        if (cert != null) 
        {
            log.setCertificateId(cert.getId());
            log.setSuccess(true);
        } 
        else 
        {
            log.setSuccess(false);
        }
        return logRepository.save(log);
    }
    public List<VerificationLog> getLogsByCertificate(Long certificateId) 
    {
        return logRepository.findByCertificateId(certificateId);
    }
}