package com.campushiring.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentProfileUpdateRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 50)
    private String department;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private java.math.BigDecimal cgpa;

    @Size(max = 15)
    @Pattern(regexp = "^[0-9+\\- ]*$")
    private String phone;

    @Size(max = 500)
    private String resumeUrl;

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