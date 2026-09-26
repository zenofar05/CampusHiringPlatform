package com.campushiring.controller;

import com.campushiring.dto.CompanyProfileResponse;
import com.campushiring.dto.CompanyProfileUpdateRequest;
import com.campushiring.entity.Company;
import com.campushiring.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyProfileController {

    private final CompanyRepository companyRepository;

    public CompanyProfileController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<CompanyProfileResponse> getProfile(Authentication authentication) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        CompanyProfileResponse response = new CompanyProfileResponse();
        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());
        response.setEmail(company.getEmail());
        response.setLocation(company.getLocation());
        response.setPackageInfo(company.getPackageInfo());
        response.setEligibilityCgpa(company.getEligibilityCgpa());
        response.setDescription(company.getDescription());
        response.setPackageAmount(company.getPackageAmount());
        response.setWebsiteUrl(company.getWebsiteUrl());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<CompanyProfileResponse> updateProfile(
            Authentication authentication,
            @Valid @RequestBody CompanyProfileUpdateRequest request) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));

        if (request.getCompanyName() != null) company.setCompanyName(request.getCompanyName());
        if (request.getEmail() != null) company.setEmail(request.getEmail());
        if (request.getLocation() != null) company.setLocation(request.getLocation());
        if (request.getPackageInfo() != null) company.setPackageInfo(request.getPackageInfo());
        if (request.getEligibilityCgpa() != null) company.setEligibilityCgpa(request.getEligibilityCgpa());
        if (request.getDescription() != null) company.setDescription(request.getDescription());
        if (request.getPackageAmount() != null) company.setPackageAmount(request.getPackageAmount());
        if (request.getWebsiteUrl() != null) company.setWebsiteUrl(request.getWebsiteUrl());

        companyRepository.save(company);

        CompanyProfileResponse response = new CompanyProfileResponse();
        response.setCompanyId(company.getCompanyId());
        response.setCompanyName(company.getCompanyName());
        response.setEmail(company.getEmail());
        response.setLocation(company.getLocation());
        response.setPackageInfo(company.getPackageInfo());
        response.setEligibilityCgpa(company.getEligibilityCgpa());
        response.setDescription(company.getDescription());
        response.setPackageAmount(company.getPackageAmount());
        response.setWebsiteUrl(company.getWebsiteUrl());
        return ResponseEntity.ok(response);
    }

    private void requireCompanyRole(Authentication authentication) {
        boolean isCompany = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"));
        if (!isCompany) {
            throw new IllegalArgumentException("Company role required");
        }
    }
}