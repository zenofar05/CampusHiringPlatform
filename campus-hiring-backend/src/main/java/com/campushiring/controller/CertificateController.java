package com.campushiring.controller;

import com.campushiring.dto.CertificateRequest;
import com.campushiring.dto.CertificateResponse;
import com.campushiring.entity.Certificate;
import com.campushiring.repository.CertificateRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateRepository certificateRepository;

    public CertificateController(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @PostMapping
    public ResponseEntity<CertificateResponse> addCertificate(
            Authentication authentication,
            @Valid @RequestBody CertificateRequest request) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());

        Certificate certificate = new Certificate();
        certificate.setStudentId(studentId);
        certificate.setCertificateName(request.getCertificateName());
        certificate.setCompanyName(request.getCompanyName());
        certificate.setIssueDate(request.getIssueDate());
        certificate.setCertificateUrl(request.getCertificateUrl());
        certificate.setCompanyId(request.getCompanyId());
        certificate.setCredentialId(request.getCredentialId());
        certificate.setCredentialUrl(request.getCredentialUrl());
        certificate.setDescription(request.getDescription());
        certificate.setExpiryDate(request.getExpiryDate());
        certificate.setFileUrl(request.getFileUrl());
        certificate.setIssuingOrganization(request.getIssuingOrganization());

        Certificate saved = certificateRepository.save(certificate);
        return ResponseEntity.ok(toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<CertificateResponse>> getMyCertificates(Authentication authentication) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());
        List<Certificate> certificates = certificateRepository.findByStudentId(studentId);
        return ResponseEntity.ok(certificates.stream().map(this::toResponse).toList());
    }

    @GetMapping("/company")
    public ResponseEntity<List<CertificateResponse>> getCompanyCertificates(Authentication authentication) {
        requireCompanyRole(authentication);
        Integer companyId = Integer.parseInt(authentication.getName());
        List<Certificate> certificates = certificateRepository.findByCompanyId(companyId);
        return ResponseEntity.ok(certificates.stream().map(this::toResponse).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(
            Authentication authentication,
            @PathVariable Integer id) {
        requireStudentRole(authentication);
        Long studentId = Long.parseLong(authentication.getName());
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Certificate not found"));
        if (!certificate.getStudentId().equals(studentId)) {
            throw new IllegalArgumentException("Not authorized to delete this certificate");
        }
        certificateRepository.delete(certificate);
        return ResponseEntity.noContent().build();
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

    private CertificateResponse toResponse(Certificate c) {
        CertificateResponse response = new CertificateResponse();
        response.setCertificateId(c.getCertificateId());
        response.setStudentId(c.getStudentId());
        response.setCertificateName(c.getCertificateName());
        response.setCompanyName(c.getCompanyName());
        response.setIssueDate(c.getIssueDate());
        response.setCertificateUrl(c.getCertificateUrl());
        response.setCompanyId(c.getCompanyId());
        response.setCredentialId(c.getCredentialId());
        response.setCredentialUrl(c.getCredentialUrl());
        response.setDescription(c.getDescription());
        response.setExpiryDate(c.getExpiryDate());
        response.setFileUrl(c.getFileUrl());
        response.setIssuingOrganization(c.getIssuingOrganization());
        response.setUpdatedAt(c.getUpdatedAt());
        return response;
    }
}