package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "companies_company_id_seq", allocationSize = 1)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "package", length = 50)
    private String packageInfo;

    @Column(name = "eligibility_cgpa", precision = 3, scale = 2)
    private java.math.BigDecimal eligibilityCgpa;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "package_amount")
    private Long packageAmount;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "website_url", length = 255)
    private String websiteUrl;

    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getPackageInfo() { return packageInfo; }
    public void setPackageInfo(String packageInfo) { this.packageInfo = packageInfo; }
    public java.math.BigDecimal getEligibilityCgpa() { return eligibilityCgpa; }
    public void setEligibilityCgpa(java.math.BigDecimal eligibilityCgpa) { this.eligibilityCgpa = eligibilityCgpa; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
}