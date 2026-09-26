package com.campushiring.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class CompanyProfileUpdateRequest {

    @Size(max = 150)
    private String companyName;

    @Size(max = 100)
    @Email
    private String email;

    @Size(max = 200)
    private String location;

    @Size(max = 50)
    private String packageInfo;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private java.math.BigDecimal eligibilityCgpa;

    private String description;

    private Long packageAmount;

    @Size(max = 255)
    private String websiteUrl;

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getPackageInfo() { return packageInfo; }
    public void setPackageInfo(String packageInfo) { this.packageInfo = packageInfo; }
    public java.math.BigDecimal getEligibilityCgpa() { return eligibilityCgpa; }
    public void setEligibilityCgpa(java.math.BigDecimal eligibilityCgpa) { this.eligibilityCgpa = eligibilityCgpa; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
}