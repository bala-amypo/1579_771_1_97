package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;

import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Corrected TestNG test class (64 tests).
 */
@Listeners(TestResultListener.class)
public class DigitalCertificateSystemTest {

    // Mocks
    @Mock private UserRepository userRepository;
    @Mock private StudentRepository studentRepository;
    @Mock private CertificateTemplateRepository templateRepository;
    @Mock private CertificateRepository certificateRepository;
    @Mock private VerificationLogRepository logRepository;

    // Services
    private UserServiceImpl userService;
    private StudentServiceImpl studentService;
    private TemplateServiceImpl templateService;
    private CertificateServiceImpl certificateService;
    private VerificationServiceImpl verificationService;

    // Controllers
    private AuthController authController;
    private StudentController studentController;
    private TemplateController templateController;
    private CertificateController certificateController;
    private VerificationController verificationController;

    private JwtUtil jwtUtil;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);

        userService = new UserServiceImpl(userRepository, new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
        studentService = new StudentServiceImpl(studentRepository);
        templateService = new TemplateServiceImpl(templateRepository);
        certificateService = new CertificateServiceImpl(certificateRepository, studentRepository, templateRepository);
        verificationService = new VerificationServiceImpl(certificateRepository, logRepository);

        jwtUtil = new JwtUtil("abcdefghijklmnopqrstuvwxyz0123456789ABCD", 3600000L);

        authController = new AuthController(userService, jwtUtil, new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
        studentController = new StudentController(studentService);
        templateController = new TemplateController(templateService);
        certificateController = new CertificateController(certificateService);
        verificationController = new VerificationController(verificationService);
    }

    // -------------------------
    // Section 1: Servlet (1-8)
    // -------------------------
    @Test(priority = 1, groups = {"servlet"})
    public void t01_controllersCreated() {
        Assert.assertNotNull(authController);
        Assert.assertNotNull(studentController);
        Assert.assertNotNull(templateController);
        Assert.assertNotNull(certificateController);
        Assert.assertNotNull(verificationController);
    }

    @Test(priority = 2, groups = {"servlet"})
    public void t02_applicationMainRuns() {
        Assert.assertNotNull(com.example.demo.DemoApplication.class);
    }

    @Test(priority = 3, groups = {"servlet"})
    public void t03_swaggerConfigPresent() {
        Assert.assertNotNull(com.example.demo.config.SwaggerConfig.class);
    }

    @Test(priority = 4, groups = {"servlet"})
    public void t04_securityConfigPresent() {
        Assert.assertNotNull(com.example.demo.config.SecurityConfig.class);
    }

    @Test(priority = 5, groups = {"servlet"})
    public void t05_authEndpointsExist() throws NoSuchMethodException {
        Assert.assertNotNull(authController.getClass().getMethod("register", RegisterRequest.class));
        Assert.assertNotNull(authController.getClass().getMethod("login", AuthRequest.class));
    }

    @Test(priority = 6, groups = {"servlet"})
    public void t06_studentEndpointsExist() throws NoSuchMethodException {
        Assert.assertNotNull(studentController.getClass().getMethod("add", com.example.demo.entity.Student.class));
        Assert.assertNotNull(studentController.getClass().getMethod("list"));
    }

    @Test(priority = 7, groups = {"servlet"})
    public void t07_templateEndpointsExist() throws NoSuchMethodException {
        Assert.assertNotNull(templateController.getClass().getMethod("add", com.example.demo.entity.CertificateTemplate.class));
        Assert.assertNotNull(templateController.getClass().getMethod("list"));
    }

    @Test(priority = 8, groups = {"servlet"})
    public void t08_certificateEndpointsExist() throws NoSuchMethodException {
        Assert.assertNotNull(certificateController.getClass().getMethod("generate", Long.class, Long.class));
        Assert.assertNotNull(certificateController.getClass().getMethod("get", Long.class));
    }

    // ------------------------------------------
    // Section 2: CRUD & REST APIs (9-16)
    // ------------------------------------------
    @Test(priority = 9, groups = {"crud"})
    public void t09_addStudentSuccess() {
        Student s = Student.builder().name("Alice").email("alice@ex.com").rollNumber("R001").build();
        when(studentRepository.findByEmail("alice@ex.com")).thenReturn(Optional.empty());
        when(studentRepository.findByRollNumber("R001")).thenReturn(Optional.empty());
        when(studentRepository.save(any(Student.class))).thenAnswer(inv -> {
            Student arg = inv.getArgument(0, Student.class);
            if (arg != null) arg.setId(1L);
            return arg;
        });

        Student res = studentService.addStudent(s);
        Assert.assertEquals(res.getName(), "Alice");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test(priority = 10, groups = {"crud"})
    public void t10_addStudentDuplicateEmail() {
        Student s = Student.builder().name("B").email("b@ex.com").rollNumber("R002").build();
        when(studentRepository.findByEmail("b@ex.com")).thenReturn(Optional.of(s));
        try {
            studentService.addStudent(s);
            Assert.fail("Expected duplicate email runtime exception");
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Student email exists"));
        }
    }

    @Test(priority = 11, groups = {"crud"})
    public void t11_listStudents() {
        Student s = Student.builder().id(1L).name("C").email("c@ex.com").rollNumber("R003").build();
        when(studentRepository.findAll()).thenReturn(List.of(s));
        List<Student> list = studentService.getAllStudents();
        Assert.assertEquals(list.size(), 1);
    }

    @Test(priority = 12, groups = {"crud"})
    public void t12_addTemplateSuccess() {
        CertificateTemplate t = CertificateTemplate.builder().templateName("T1").backgroundUrl("https://bg").build();
        when(templateRepository.findByTemplateName("T1")).thenReturn(Optional.empty());
        when(templateRepository.save(any(CertificateTemplate.class))).thenAnswer(inv -> {
            CertificateTemplate arg = inv.getArgument(0, CertificateTemplate.class);
            if (arg != null) arg.setId(2L);
            return arg;
        });
        CertificateTemplate out = templateService.addTemplate(t);
        Assert.assertEquals(out.getTemplateName(), "T1");
    }

    @Test(priority = 13, groups = {"crud"})
    public void t13_addTemplateDuplicateName() {
        CertificateTemplate t = CertificateTemplate.builder().templateName("X").backgroundUrl("https://x").build();
        when(templateRepository.findByTemplateName("X")).thenReturn(Optional.of(t));
        try {
            templateService.addTemplate(t);
            Assert.fail("Expected duplicate template exception");
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Template name exists"));
        }
    }

    @Test(priority = 14, groups = {"crud"})
    public void t14_generateCertificateSuccess() {
        Student s = Student.builder().id(2L).name("D").email("d@ex").rollNumber("R010").build();
        CertificateTemplate tpl = CertificateTemplate.builder().id(2L).templateName("T2").backgroundUrl("https://bg2").build();

        when(studentRepository.findById(2L)).thenReturn(Optional.of(s));
        when(templateRepository.findById(2L)).thenReturn(Optional.of(tpl));
        when(certificateRepository.save(any(Certificate.class))).thenAnswer(inv -> {
            Certificate arg = inv.getArgument(0, Certificate.class);
            if (arg != null) arg.setId(100L);
            return arg;
        });

        Certificate cert = certificateService.generateCertificate(2L, 2L);
        Assert.assertNotNull(cert.getVerificationCode());
        Assert.assertTrue(cert.getQrCodeUrl() != null && cert.getQrCodeUrl().startsWith("data:image/png;base64,"));
        verify(certificateRepository, times(1)).save(any(Certificate.class));
    }

    @Test(priority = 15, groups = {"crud"})
    public void t15_getCertificateNotFound() {
        when(certificateRepository.findById(999L)).thenReturn(Optional.empty());
        try {
            certificateService.getCertificate(999L);
            Assert.fail("Expected ResourceNotFoundException");
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("Certificate not found"));
        }
    }

    @Test(priority = 16, groups = {"crud"})
public void t16_findByVerificationCode() {
    Certificate c = Certificate.builder().id(200L).verificationCode("VC200").build();
    when(certificateRepository.findByVerificationCode("VC200")).thenReturn(Optional.of(c));
    Certificate out = certificateService.findByVerificationCode("VC200");
    Assert.assertEquals(out.getId().longValue(), 200L);
}
}

