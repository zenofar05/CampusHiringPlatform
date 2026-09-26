package com.campushiring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Campus Hiring Platform API is running";
    }

    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}