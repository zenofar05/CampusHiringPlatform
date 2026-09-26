package com.campushiring.controller;

import com.campushiring.dto.LoginRequest;
import com.campushiring.dto.LoginResponse;
import com.campushiring.entity.Student;
import com.campushiring.entity.Company;
import com.campushiring.entity.User;
import com.campushiring.repository.StudentRepository;
import com.campushiring.repository.CompanyRepository;
import com.campushiring.repository.UserRepository;
import com.campushiring.security.JwtUtil;
import com.campushiring.security.PlainTextPasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PlainTextPasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil,
                          StudentRepository studentRepository,
                          CompanyRepository companyRepository,
                          UserRepository userRepository,
                          PlainTextPasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/student/login")
    public ResponseEntity<LoginResponse> studentLogin(@Valid @RequestBody LoginRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail());
        if (student == null || !passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(student.getStudentId().toString(), "STUDENT", student.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole("STUDENT");
        response.setId(student.getStudentId().toString());
        response.setEmail(student.getEmail());
        response.setName(student.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/company/login")
    public ResponseEntity<LoginResponse> companyLogin(@Valid @RequestBody LoginRequest request) {
        Company company = companyRepository.findByEmail(request.getEmail());
        if (company == null || !passwordEncoder.matches(request.getPassword(), company.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(company.getCompanyId().toString(), "COMPANY", company.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole("COMPANY");
        response.setId(company.getCompanyId().toString());
        response.setEmail(company.getEmail());
        response.setName(company.getCompanyName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponse> adminLogin(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || !user.getIsActive() || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(user.getId().toString(), user.getRole(), user.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole(user.getRole());
        response.setId(user.getId().toString());
        response.setEmail(user.getEmail());
        response.setName(user.getEmail());
        return ResponseEntity.ok(response);
    }
}