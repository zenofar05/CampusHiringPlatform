package com.campushiring.controller;

import com.campushiring.dto.ApplicationRequest;
import com.campushiring.dto.ApplicationResponse;
import com.campushiring.dto.ApplicationStatusUpdateRequest;
import com.campushiring.entity.Application;
import com.campushiring.entity.Job;
import com.campushiring.entity.Student;
import com.campushiring.repository.ApplicationRepository;
import com.campushiring.repository.JobRepository;
import com.campushiring.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentRepository studentRepository;

    public ApplicationController(ApplicationRepository applicationRepository,
                                 JobRepository jobRepository,
                                 StudentRepository studentRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> applyToJob(
            Authentication authentication,
            @Valid @RequestBody ApplicationRequest request) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());

        // Check if already applied
        Optional<Application> existing = applicationRepository.findByStudentIdAndJobId(studentId, request.getJobId());
        if (existing.isPresent()) {
            throw new IllegalStateException("Already applied to this job");
        }

        // Verify job exists
        Job job = jobRepository.findById(request.getJobId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));

        Application application = new Application();
        application.setStudentId(studentId);
        application.setJobId(request.getJobId());
        application.setApplicationDate(LocalDate.now());
        application.setStatus("Applied");
        application.setCoverLetter(request.getCoverLetter());

        Application saved = applicationRepository.save(application);
        return ResponseEntity.ok(toResponse(saved, job, null));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(Authentication authentication) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());
        List<Application> applications = applicationRepository.findByStudentId(studentId);
        return ResponseEntity.ok(applications.stream()
                .map(a -> toResponse(a, jobRepository.findById(a.getJobId().intValue()).orElse(null), null))
                .toList());
    }

    @GetMapping("/company")
    public ResponseEntity<List<ApplicationResponse>> getCompanyApplications(Authentication authentication) {
        requireCompanyRole(authentication);
        Long companyId = Long.parseLong(authentication.getName());
        List<Application> applications = applicationRepository.findByCompanyIdViaJob(companyId);
        return ResponseEntity.ok(applications.stream()
                .map(a -> {
                    Job job = jobRepository.findById(a.getJobId().intValue()).orElse(null);
                    Student student = studentRepository.findById(a.getStudentId().intValue()).orElse(null);
                    return toResponse(a, job, student);
                })
                .toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            Authentication authentication,
            @PathVariable Integer id,
            @Valid @RequestBody ApplicationStatusUpdateRequest request) {
        requireCompanyRole(authentication);
        Long companyId = Long.parseLong(authentication.getName());
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        // Verify ownership
        Job job = jobRepository.findById(application.getJobId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        if (!job.getCompanyId().equals(companyId)) {
            throw new IllegalArgumentException("Not authorized to update this application");
        }

        application.setStatus(request.getStatus());
        Application saved = applicationRepository.save(application);
        return ResponseEntity.ok(toResponse(saved, job, studentRepository.findById(saved.getStudentId().intValue()).orElse(null)));
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

    private ApplicationResponse toResponse(Application a, Job job, Student student) {
        ApplicationResponse response = new ApplicationResponse();
        response.setApplicationId(a.getApplicationId());
        response.setStudentId(a.getStudentId());
        response.setJobId(a.getJobId());
        response.setApplicationDate(a.getApplicationDate());
        response.setStatus(a.getStatus());
        response.setCoverLetter(a.getCoverLetter());
        response.setUpdatedAt(a.getUpdatedAt());
        response.setInternshipId(a.getInternshipId());

        if (job != null) {
            response.setJobTitle(job.getJobTitle());
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