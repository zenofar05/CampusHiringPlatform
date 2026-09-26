package com.campushiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CertificateResponse {

    private Integer certificateId;
    private Long studentId;
    private String certificateName;
    private String companyName;
    private LocalDate issueDate;
    private String certificateUrl;
    private Integer companyId;
    private String credentialId;
    private String credentialUrl;
    private String description;
    private LocalDate expiryDate;
    private String fileUrl;
    private String issuingOrganization;
    private LocalDateTime updatedAt;

    public Integer getCertificateId() { return certificateId; }
    public void setCertificateId(Integer certificateId) { this.certificateId = certificateId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
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
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}