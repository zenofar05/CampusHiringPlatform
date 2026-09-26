package com.campushiring.dto;

import jakarta.validation.constraints.NotNull;

public class InternshipApplicationRequest {

    @NotNull
    private Integer internshipId;

    public Integer getInternshipId() { return internshipId; }
    public void setInternshipId(Integer internshipId) { this.internshipId = internshipId; }
}