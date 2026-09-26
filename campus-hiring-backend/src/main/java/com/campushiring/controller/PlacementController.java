package com.campushiring.controller;

import com.campushiring.dto.PlacementResponse;
import com.campushiring.entity.Placement;
import com.campushiring.repository.PlacementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    private final PlacementRepository placementRepository;

    public PlacementController(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<List<PlacementResponse>> getMyPlacements(Authentication authentication) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());
        List<Placement> placements = placementRepository.findByStudentId(studentId);
        return ResponseEntity.ok(placements.stream().map(this::toResponse).toList());
    }

    @GetMapping("/company")
    public ResponseEntity<List<PlacementResponse>> getCompanyPlacements(Authentication authentication) {
        requireCompanyRole(authentication);
        Long companyId = Long.parseLong(authentication.getName());
        List<Placement> placements = placementRepository.findByCompanyId(companyId);
        return ResponseEntity.ok(placements.stream().map(this::toResponse).toList());
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

    private PlacementResponse toResponse(Placement p) {
        PlacementResponse response = new PlacementResponse();
        response.setPlacementId(p.getPlacementId());
        response.setStudentId(p.getStudentId());
        response.setCompanyId(p.getCompanyId());
        response.setJobId(p.getJobId());
        response.setPlacementDate(p.getPlacementDate());
        response.setPackageInfo(p.getPackageInfo());
        response.setStatus(p.getStatus());
        response.setJobTitle(p.getJobTitle());
        response.setJoiningDate(p.getJoiningDate());
        response.setOfferLetterUrl(p.getOfferLetterUrl());
        response.setPackageAmount(p.getPackageAmount());
        response.setUpdatedAt(p.getUpdatedAt());
        return response;
    }
}