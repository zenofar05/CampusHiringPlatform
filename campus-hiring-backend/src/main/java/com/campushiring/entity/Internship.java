package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "internships")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "internship_seq")
    @SequenceGenerator(name = "internship_seq", sequenceName = "internships_internship_id_seq", allocationSize = 1)
    @Column(name = "internship_id")
    private Integer internshipId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "duration", length = 50)
    private String duration;

    @Column(name = "stipend", length = 50)
    private String stipend;

    @Column(name = "eligibility_cgpa", precision = 3, scale = 2)
    private java.math.BigDecimal eligibilityCgpa;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "skills", length = 500)
    private String skills;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "updated_at")
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}