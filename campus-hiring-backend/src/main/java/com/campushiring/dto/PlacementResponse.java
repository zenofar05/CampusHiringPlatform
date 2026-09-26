package com.campushiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlacementResponse {

    private Integer placementId;
    private Long studentId;
    private Long companyId;
    private Integer jobId;
    private LocalDate placementDate;
    private String packageInfo;
    private String status;
    private String jobTitle;
    private LocalDate joiningDate;
    private String offerLetterUrl;
    private Long packageAmount;
    private LocalDateTime updatedAt;

    public Integer getPlacementId() { return placementId; }
    public void setPlacementId(Integer placementId) { this.placementId = placementId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }
    public LocalDate getPlacementDate() { return placementDate; }
    public void setPlacementDate(LocalDate placementDate) { this.placementDate = placementDate; }
    public String getPackageInfo() { return packageInfo; }
    public void setPackageInfo(String packageInfo) { this.packageInfo = packageInfo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
    public String getOfferLetterUrl() { return offerLetterUrl; }
    public void setOfferLetterUrl(String offerLetterUrl) { this.offerLetterUrl = offerLetterUrl; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}