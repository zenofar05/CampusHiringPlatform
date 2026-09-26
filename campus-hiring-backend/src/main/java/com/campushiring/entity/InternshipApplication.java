package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "internship_applications")
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "internship_app_seq")
    @SequenceGenerator(name = "internship_app_seq", sequenceName = "internship_applications_internship_application_id_seq", allocationSize = 1)
    @Column(name = "internship_application_id")
    private Integer internshipApplicationId;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "internship_id", nullable = false)
    private Integer internshipId;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "status", length = 30)
    private String status;

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
}