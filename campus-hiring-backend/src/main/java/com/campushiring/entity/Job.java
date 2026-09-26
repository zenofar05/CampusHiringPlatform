package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    @SequenceGenerator(name = "job_seq", sequenceName = "jobs_job_id_seq", allocationSize = 1)
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "job_title", nullable = false, length = 150)
    private String jobTitle;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "salary", length = 50)
    private String salary;

    @Column(name = "eligibility_cgpa", precision = 3, scale = 2)
    private java.math.BigDecimal eligibilityCgpa;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "package_amount")
    private Long packageAmount;

    @Column(name = "skills", length = 500)
    private String skills;

    @Column(name = "updated_at")
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}