package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "students_student_id_seq", allocationSize = 1)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "department", length = 50)
    private String department;

    @Column(name = "cgpa", precision = 3, scale = 2)
    private java.math.BigDecimal cgpa;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "resume_url", length = 500)
    private String resumeUrl;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public java.math.BigDecimal getCgpa() { return cgpa; }
    public void setCgpa(java.math.BigDecimal cgpa) { this.cgpa = cgpa; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}