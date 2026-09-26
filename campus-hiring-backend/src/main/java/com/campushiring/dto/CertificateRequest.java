package com.campushiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CertificateRequest {

    @NotBlank
    @Size(max = 200)
    private String certificateName;

    @Size(max = 100)
    private String companyName;

    @NotNull
    private LocalDate issueDate;

    @Size(max = 1000)
    private String certificateUrl;

    private Integer companyId;

    @Size(max = 100)
    private String credentialId;

    @Size(max = 500)
    private String credentialUrl;

    @Size(max = 5000)
    private String description;

    private LocalDate expiryDate;

    @Size(max = 500)
    private String fileUrl;

    @Size(max = 200)
    private String issuingOrganization;

    public String getCertificateName() { return certificateName; }
    public void setCertificateName(String certificateName) { this.certificateName = certificateName; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public String getCertificateUrl() { return certificateUrl; }
    public void setCertificateUrl(String certificateUrl) { this.certificateUrl = certificateUrl; }
    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
    public String getCredentialId() { return credentialId; }
    public void setCredentialId(String credentialId) { this.credentialId = credentialId; }
    public String getCredentialUrl() { return credentialUrl; }
    public void setCredentialUrl(String credentialUrl) { this.credentialUrl = credentialUrl; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public String getIssuingOrganization() { return issuingOrganization; }
    public void setIssuingOrganization(String issuingOrganization) { this.issuingOrganization = issuingOrganization; }
}