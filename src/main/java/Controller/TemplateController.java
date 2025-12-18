@RestController
@RequestMapping("/templates")
public class TemplateController 
{
    @Autowired
    private TemplateService templateService;
    @PostMapping("/")
    public ResponseEntity<CertificateTemplate> addTemplate(@RequestBody CertificateTemplate template) 
    {
        return ResponseEntity.ok(templateService.addTemplate(template));
    }
    @GetMapping("/")
    public ResponseEntity<List<CertificateTemplate>> listTemplates()
    {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
}