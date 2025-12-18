@RestController
@RequestMapping("/verify")
public class VerificationController 
{
    @Autowired
    private VerificationService verificationService;
    @PostMapping("/{verificationCode}")
    public ResponseEntity<VerificationLog> verifyCertificate(
            @PathVariable String verificationCode, 
            HttpServletRequest request) 
    {
        String clientIp = request.getRemoteAddr();
        return ResponseEntity.ok(verificationService.verifyCertificate(verificationCode, clientIp));
    }

    @GetMapping("/logs/{certificateId}")
    public ResponseEntity<List<VerificationLog>> viewLogs(@PathVariable Long certificateId) 
    {
        return ResponseEntity.ok(verificationService.getLogsByCertificate(certificateId));
    }
}