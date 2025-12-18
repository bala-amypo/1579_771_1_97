@Service
public class TemplateService 
{
    @Autowired
    private TemplateRepository templateRepository;
    public CertificateTemplate addTemplate(CertificateTemplate template) 
    {
        if (templateRepository.existsByName(template.getName())) 
        {
            throw new RuntimeException("Template name exists");
        }
        return templateRepository.save(template);
    }
    public List<CertificateTemplate> getAllTemplates() 
    {
        return templateRepository.findAll();
    }
    public CertificateTemplate findById(Long id) 
    {
        return templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }
}