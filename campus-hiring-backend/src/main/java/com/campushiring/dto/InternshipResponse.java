package com.campushiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InternshipResponse {

    private Integer internshipId;
    private Long companyId;
    private String title;
    private String description;
    private String location;
    private String duration;
    private String stipend;
    private java.math.BigDecimal eligibilityCgpa;
    private LocalDate deadline;
    private LocalDate startDate;
    private LocalDate endDate;
    private String skills;
    private LocalDateTime updatedAt;

    public Integer getInternshipId() { return internshipId; }
    public void setInternshipId(Integer internshipId) { this.internshipId = internshipId; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getStipend() { return stipend; }
    public void setStipend(String stipend) { this.stipend = stipend; }
    public java.math.BigDecimal getEligibilityCgpa() { return eligibilityCgpa; }
    public void setEligibilityCgpa(java.math.BigDecimal eligibilityCgpa) { this.eligibilityCgpa = eligibilityCgpa; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}