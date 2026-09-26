package com.campushiring.dto;

import java.time.LocalDate;

public class InternshipApplicationResponse {

    private Integer internshipApplicationId;
    private Integer studentId;
    private Integer internshipId;
    private LocalDate applicationDate;
    private String status;

    // Internship details
    private String internshipTitle;
    private String companyName;

    // Student details
    private String studentName;
    private String studentEmail;
    private String studentDepartment;
    private java.math.BigDecimal studentCgpa;

    public Integer getInternshipApplicationId() { return internshipApplicationId; }
    public void setInternshipApplicationId(Integer internshipApplicationId) { this.internshipApplicationId = internshipApplicationId; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getInternshipId() { return internshipId; }
    public void setInternshipId(Integer internshipId) { this.internshipId = internshipId; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getInternshipTitle() { return internshipTitle; }
    public void setInternshipTitle(String internshipTitle) { this.internshipTitle = internshipTitle; }
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