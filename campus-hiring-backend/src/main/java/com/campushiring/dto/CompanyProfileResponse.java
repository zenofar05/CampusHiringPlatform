package com.campushiring.dto;

public class CompanyProfileResponse {

    private Integer companyId;
    private String companyName;
    private String email;
    private String location;
    private String packageInfo;
    private java.math.BigDecimal eligibilityCgpa;
    private String description;
    private Long packageAmount;
    private String websiteUrl;

    public Integer getCompanyId() { return companyId; }
    public void setCompanyId(Integer companyId) { this.companyId = companyId; }
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