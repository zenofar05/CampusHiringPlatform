package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    @SequenceGenerator(name = "application_seq", sequenceName = "applications_application_id_seq", allocationSize = 1)
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "cover_letter", columnDefinition = "text")
    private String coverLetter;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "internship_id")
    private Long internshipId;

    public Integer getApplicationId() { return applicationId; }
    public void setApplicationId(Integer applicationId) { this.applicationId = applicationId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }
}