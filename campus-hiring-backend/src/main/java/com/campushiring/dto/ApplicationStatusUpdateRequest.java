package com.campushiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ApplicationStatusUpdateRequest {

    @NotBlank
    @Size(max = 20)
    private String status;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}