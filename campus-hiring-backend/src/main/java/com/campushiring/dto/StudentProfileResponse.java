package com.campushiring.dto;

public class StudentProfileResponse {

    private Integer studentId;
    private String name;
    private String email;
    private String department;
    private java.math.BigDecimal cgpa;
    private String phone;
    private String resumeUrl;

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public java.math.BigDecimal getCgpa() { return cgpa; }
    public void setCgpa(java.math.BigDecimal cgpa) { this.cgpa = cgpa; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
}