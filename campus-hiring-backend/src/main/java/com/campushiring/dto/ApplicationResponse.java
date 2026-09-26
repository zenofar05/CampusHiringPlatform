package com.campushiring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApplicationResponse {

    private Integer applicationId;
    private Long studentId;
    private Long jobId;
    private LocalDate applicationDate;
    private String status;
    private String coverLetter;
    private LocalDateTime updatedAt;
    private Long internshipId;

    // Job details for company view
    private String jobTitle;
    private String companyName;

    // Student details for company view
    private String studentName;
    private String studentEmail;
    private String studentDepartment;
    private java.math.BigDecimal studentCgpa;

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
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Long getInternshipId() { return internshipId; }
    public void setInternshipId(Long internshipId) { this.internshipId = internshipId; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public String getStudentDepartment() { return studentDepartment; }
    public void setStudentDepartment(String studentDepartment) { this.studentDepartment = studentDepartment; }
    public java.math.BigDecimal getStudentCgpa() { return studentCgpa; }
    public void setStudentCgpa(java.math.BigDecimal studentCgpa) { this.studentCgpa = studentCgpa; }
}