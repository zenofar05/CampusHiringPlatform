package com.campushiring.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "placements")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placement_seq")
    @SequenceGenerator(name = "placement_seq", sequenceName = "placements_placement_id_seq", allocationSize = 1)
    @Column(name = "placement_id")
    private Integer placementId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "job_id", nullable = false)
    private Integer jobId;

    @Column(name = "placement_date")
    private LocalDate placementDate;

    @Column(name = "package", length = 50)
    private String packageInfo;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "job_title", length = 150)
    private String jobTitle;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "offer_letter_url", length = 500)
    private String offerLetterUrl;

    @Column(name = "package_amount")
    private Long packageAmount;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getPlacementId() { return placementId; }
    public void setPlacementId(Integer placementId) { this.placementId = placementId; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public Integer getJobId() { return jobId; }
    public void setJobId(Integer jobId) { this.jobId = jobId; }
    public LocalDate getPlacementDate() { return placementDate; }
    public void setPlacementDate(LocalDate placementDate) { this.placementDate = placementDate; }
    public String getPackageInfo() { return packageInfo; }
    public void setPackageInfo(String packageInfo) { this.packageInfo = packageInfo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
    public String getOfferLetterUrl() { return offerLetterUrl; }
    public void setOfferLetterUrl(String offerLetterUrl) { this.offerLetterUrl = offerLetterUrl; }
    public Long getPackageAmount() { return packageAmount; }
    public void setPackageAmount(Long packageAmount) { this.packageAmount = packageAmount; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}