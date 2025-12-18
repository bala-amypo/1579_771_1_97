@RestController
@RequestMapping("/certificates")
public class CertificateController 
{
    @Autowired
    private CertificateService certificateService;
    @PostMapping("/generate/{studentId}/{templateId}")
    public ResponseEntity<Certificate> generateCertificate(
            @PathVariable Long studentId, 
            @PathVariable Long templateId) 
    {
        return ResponseEntity.ok(certificateService.generateCertificate(studentId, templateId));
    }
    @GetMapping("/{certificateId}")
    public ResponseEntity<Certificate> getCertificateDetails(@PathVariable Long certificateId) 
    {
        return ResponseEntity.ok(certificateService.getCertificate(certificateId));
    }
    @GetMapping("/verify/code/{verificationCode}")
    public ResponseEntity<Certificate> fetchByCode(@PathVariable String verificationCode) 
    {
        return ResponseEntity.ok(certificateService.findByVerificationCode(verificationCode));
    }
}