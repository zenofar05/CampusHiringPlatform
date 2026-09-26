package com.campushiring.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class InternshipRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 5000)
    private String description;

    @Size(max = 100)
    private String location;

    @Size(max = 50)
    private String duration;

    @Size(max = 50)
    private String stipend;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private java.math.BigDecimal eligibilityCgpa;

    @NotNull
    private LocalDate deadline;

    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 500)
    private String skills;

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
}