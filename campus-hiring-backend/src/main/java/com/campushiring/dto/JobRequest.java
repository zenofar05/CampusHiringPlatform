package com.campushiring.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class JobRequest {

    @NotBlank
    @Size(max = 150)
    private String jobTitle;

    @Size(max = 5000)
    private String description;

    @Size(max = 100)
    private String location;

    @Size(max = 50)
    private String salary;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private java.math.BigDecimal eligibilityCgpa;

    @NotNull
    private LocalDate deadline;

    private Long packageAmount;

    @Size(max = 500)
    private String skills;

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
}