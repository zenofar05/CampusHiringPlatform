package com.campushiring.controller;

import com.campushiring.dto.InternshipRequest;
import com.campushiring.dto.InternshipResponse;
import com.campushiring.entity.Internship;
import com.campushiring.repository.InternshipRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
public class InternshipController {

    private final InternshipRepository internshipRepository;

    public InternshipController(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @GetMapping
    public ResponseEntity<List<InternshipResponse>> getAllInternships() {
        List<Internship> internships = internshipRepository.findAll();
        return ResponseEntity.ok(internships.stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipResponse> getInternship(@PathVariable Integer id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
        return ResponseEntity.ok(toResponse(internship));
    }

    @PostMapping
    public ResponseEntity<InternshipResponse> createInternship(
            Authentication authentication,
            @Valid @RequestBody InternshipRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Internship internship = new Internship();
        internship.setCompanyId(companyId.longValue());
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setDuration(request.getDuration());
        internship.setStipend(request.getStipend());
        internship.setEligibilityCgpa(request.getEligibilityCgpa());
        internship.setDeadline(request.getDeadline());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
        internship.setSkills(request.getSkills());
        Internship saved = internshipRepository.save(internship);
        return ResponseEntity.ok(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipResponse> updateInternship(
            Authentication authentication,
            @PathVariable Integer id,
            @Valid @RequestBody InternshipRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
        if (!internship.getCompanyId().equals(companyId.longValue())) {
            throw new IllegalArgumentException("Not authorized to update this internship");
        }
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setDuration(request.getDuration());
        internship.setStipend(request.getStipend());
        internship.setEligibilityCgpa(request.getEligibilityCgpa());
        internship.setDeadline(request.getDeadline());
        internship.setStartDate(request.getStartDate());
        internship.setEndDate(request.getEndDate());
        internship.setSkills(request.getSkills());
        Internship saved = internshipRepository.save(internship);
        return ResponseEntity.ok(toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(
            Authentication authentication,
            @PathVariable Integer id) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Internship not found"));
        if (!internship.getCompanyId().equals(companyId.longValue())) {
            throw new IllegalArgumentException("Not authorized to delete this internship");
        }
        internshipRepository.delete(internship);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    public ResponseEntity<List<InternshipResponse>> getMyInternships(Authentication authentication) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        List<Internship> internships = internshipRepository.findByCompanyId(companyId.longValue());
        return ResponseEntity.ok(internships.stream().map(this::toResponse).toList());
    }

    private void requireCompanyRole(Authentication authentication) {
        boolean isCompany = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"));
        if (!isCompany) {
            throw new IllegalArgumentException("Company role required");
        }
    }

    private InternshipResponse toResponse(Internship internship) {
        InternshipResponse response = new InternshipResponse();
        response.setInternshipId(internship.getInternshipId());
        response.setCompanyId(internship.getCompanyId());
        response.setTitle(internship.getTitle());
        response.setDescription(internship.getDescription());
        response.setLocation(internship.getLocation());
        response.setDuration(internship.getDuration());
        response.setStipend(internship.getStipend());
        response.setEligibilityCgpa(internship.getEligibilityCgpa());
        response.setDeadline(internship.getDeadline());
        response.setStartDate(internship.getStartDate());
        response.setEndDate(internship.getEndDate());
        response.setSkills(internship.getSkills());
        response.setUpdatedAt(internship.getUpdatedAt());
        return response;
    }
}