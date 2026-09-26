package com.campushiring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationRequest {

    @NotNull
    private Long jobId;

    @Size(max = 5000)
    private String coverLetter;

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
}