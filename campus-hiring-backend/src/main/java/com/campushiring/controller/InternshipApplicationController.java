package com.campushiring.controller;

import com.campushiring.dto.InternshipApplicationRequest;
import com.campushiring.dto.InternshipApplicationResponse;
import com.campushiring.dto.ApplicationStatusUpdateRequest;
import com.campushiring.entity.Internship;
import com.campushiring.entity.InternshipApplication;
import com.campushiring.entity.Student;
import com.campushiring.repository.InternshipApplicationRepository;
import com.campushiring.repository.InternshipRepository;
import com.campushiring.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internship-applications")
public class InternshipApplicationController {

    private final InternshipApplicationRepository internshipApplicationRepository;
    private final InternshipRepository internshipRepository;
    private final StudentRepository studentRepository;

    public InternshipApplicationController(InternshipApplicationRepository internshipApplicationRepository,
                                           InternshipRepository internshipRepository,
                                           StudentRepository studentRepository) {
        this.internshipApplicationRepository = internshipApplicationRepository;
        this.internshipRepository = internshipRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<InternshipApplicationResponse> applyToInternship(
            Authentication authentication,
            @Valid @RequestBody InternshipApplicationRequest request) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());

        // Check if already applied
        Optional<InternshipApplication> existing = internshipApplicationRepository
                .findByStudentIdAndInternshipId(studentId, request.getInternshipId());
        if (existing.isPresent()) {
            throw new IllegalStateException("Already applied to this internship");
        }

        // Verify internship exists
        Internship internship = internshipRepository.findById(request.getInternshipId())
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));

        InternshipApplication application = new InternshipApplication();
        application.setStudentId(studentId);
        application.setInternshipId(request.getInternshipId());
        application.setApplicationDate(LocalDate.now());
        application.setStatus("Applied");

        InternshipApplication saved = internshipApplicationRepository.save(application);
        return ResponseEntity.ok(toResponse(saved, internship, null));
    }

    @GetMapping
    public ResponseEntity<List<InternshipApplicationResponse>> getMyApplications(Authentication authentication) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());
        List<InternshipApplication> applications = internshipApplicationRepository.findByStudentId(studentId);
        return ResponseEntity.ok(applications.stream()
                .map(a -> toResponse(a, internshipRepository.findById(a.getInternshipId()).orElse(null), null))
                .toList());
    }

    @GetMapping("/company")
    public ResponseEntity<List<InternshipApplicationResponse>> getCompanyApplications(Authentication authentication) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        // Get internships for this company, then applications for those internships
        List<Internship> companyInternships = internshipRepository.findByCompanyId(companyId.longValue());
        List<InternshipApplication> applications = companyInternships.stream()
                .flatMap(i -> internshipApplicationRepository.findByInternshipId(i.getInternshipId()).stream())
                .toList();
        return ResponseEntity.ok(applications.stream()
                .map(a -> {
                    Internship internship = internshipRepository.findById(a.getInternshipId()).orElse(null);
                    Student student = studentRepository.findById(a.getStudentId()).orElse(null);
                    return toResponse(a, internship, student);
                })
                .toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InternshipApplicationResponse> updateStatus(
            Authentication authentication,
            @PathVariable Integer id,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        InternshipApplication application = internshipApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        // Verify ownership
        Internship internship = internshipRepository.findById(application.getInternshipId())
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
        if (!internship.getCompanyId().equals(companyId.longValue())) {
            throw new IllegalArgumentException("Not authorized to update this application");
        }

        application.setStatus(request.getStatus());
        InternshipApplication saved = internshipApplicationRepository.save(application);
        return ResponseEntity.ok(toResponse(saved, internship, studentRepository.findById(saved.getStudentId()).orElse(null)));
    }

    private void requireStudentRole(Authentication authentication) {
        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
        if (!isStudent) {
            throw new IllegalArgumentException("Student role required");
        }
    }

    private void requireCompanyRole(Authentication authentication) {
        boolean isCompany = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"));
        if (!isCompany) {
            throw new IllegalArgumentException("Company role required");
        }
    }

    private InternshipApplicationResponse toResponse(InternshipApplication a, Internship internship, Student student) {
        InternshipApplicationResponse response = new InternshipApplicationResponse();
        response.setInternshipApplicationId(a.getInternshipApplicationId());
        response.setStudentId(a.getStudentId());
        response.setInternshipId(a.getInternshipId());
        response.setApplicationDate(a.getApplicationDate());
        response.setStatus(a.getStatus());

        if (internship != null) {
            response.setInternshipTitle(internship.getTitle());
        }
        if (student != null) {
            response.setStudentName(student.getName());
            response.setStudentEmail(student.getEmail());
            response.setStudentDepartment(student.getDepartment());
            response.setStudentCgpa(student.getCgpa());
        }
        return response;
    }
}