package com.campushiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobResponse {

    private Integer jobId;
    private Long companyId;
    private String jobTitle;
    private String description;
    private String location;
    private String salary;
    private java.math.BigDecimal eligibilityCgpa;
    private LocalDate deadline;
    private Long packageAmount;
    private String skills;
    private LocalDateTime updatedAt;

    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public java.math.BigDecimal getEligibilityCgpa() { return eligibilityCgpa; }
    public void setEligibilityCgpa(java.math.BigDecimal eligibilityCgpa) { this.eligibilityCgpa = eligibilityCgpa; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}