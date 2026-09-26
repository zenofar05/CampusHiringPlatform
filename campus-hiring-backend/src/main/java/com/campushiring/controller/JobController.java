package com.campushiring.controller;

import com.campushiring.dto.JobRequest;
import com.campushiring.dto.JobResponse;
import com.campushiring.entity.Job;
import com.campushiring.repository.JobRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return ResponseEntity.ok(jobs.stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJob(@PathVariable Integer id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        return ResponseEntity.ok(toResponse(job));
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            Authentication authentication,
            @Valid @RequestBody JobRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Job job = new Job();
        job.setCompanyId(companyId.longValue());
        job.setJobTitle(request.getJobTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setEligibilityCgpa(request.getEligibilityCgpa());
        job.setDeadline(request.getDeadline());
        job.setPackageAmount(request.getPackageAmount());
        job.setSkills(request.getSkills());
        Job saved = jobRepository.save(job);
        return ResponseEntity.ok(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            Authentication authentication,
            @PathVariable Integer id,
            @Valid @RequestBody JobRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        if (!job.getCompanyId().equals(companyId.longValue())) {
            throw new IllegalArgumentException("Not authorized to update this job");
        }
        job.setJobTitle(request.getJobTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setSalary(request.getSalary());
        job.setEligibilityCgpa(request.getEligibilityCgpa());
        job.setDeadline(request.getDeadline());
        job.setPackageAmount(request.getPackageAmount());
        job.setSkills(request.getSkills());
        Job saved = jobRepository.save(job);
        return ResponseEntity.ok(toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(
            Authentication authentication,
            @PathVariable Integer id) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        if (!job.getCompanyId().equals(companyId.longValue())) {
            throw new IllegalArgumentException("Not authorized to delete this job");
        }
        jobRepository.delete(job);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<JobResponse>> getMyJobs(Authentication authentication) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        List<Job> jobs = jobRepository.findByCompanyId(companyId.longValue());
        return ResponseEntity.ok(jobs.stream().map(this::toResponse).toList());
    }

    private void requireCompanyRole(Authentication authentication) {
        boolean isCompany = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"));
        if (!isCompany) {
            throw new IllegalArgumentException("Company role required");
        }
    }

    private JobResponse toResponse(Job job) {
        JobResponse response = new JobResponse();
        response.setJobId(job.getJobId());
        response.setCompanyId(job.getCompanyId());
        response.setJobTitle(job.getJobTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setSalary(job.getSalary());
        response.setEligibilityCgpa(job.getEligibilityCgpa());
        response.setDeadline(job.getDeadline());
        response.setPackageAmount(job.getPackageAmount());
        response.setSkills(job.getSkills());
        response.setUpdatedAt(job.getUpdatedAt());
        return response;
    }
}