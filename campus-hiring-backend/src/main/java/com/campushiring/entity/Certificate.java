package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certificate_seq")
    @SequenceGenerator(name = "certificate_seq", sequenceName = "certificates_certificate_id_seq", allocationSize = 1)
    @Column(name = "certificate_id")
    private Integer certificateId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "certificate_name", nullable = false, length = 200)
    private String certificateName;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "certificate_url", columnDefinition = "text")
    private String certificateUrl;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "credential_id", length = 100)
    private String credentialId;

    @Column(name = "credential_url", length = 500)
    private String credentialUrl;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "file_url", length = 500)
    private String fileUrl;

    @Column(name = "issuing_organization", length = 200)
    private String issuingOrganization;

    @Column(name = "updated_at")
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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