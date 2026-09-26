package com.campushiring.controller;

import com.campushiring.dto.StudentProfileResponse;
import com.campushiring.dto.StudentProfileUpdateRequest;
import com.campushiring.entity.Student;
import com.campushiring.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentRepository studentRepository;

    public StudentProfileController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<StudentProfileResponse> getProfile(Authentication authentication) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StudentProfileResponse response = new StudentProfileResponse();
        response.setStudentId(student.getStudentId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setDepartment(student.getDepartment());
        response.setCgpa(student.getCgpa());
        response.setPhone(student.getPhone());
        response.setResumeUrl(student.getResumeUrl());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<StudentProfileResponse> updateProfile(
            Authentication authentication,
            @Valid @RequestBody StudentProfileUpdateRequest request) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (request.getName() != null) student.setName(request.getName());
        if (request.getEmail() != null) student.setEmail(request.getEmail());
        if (request.getDepartment() != null) student.setDepartment(request.getDepartment());
        if (request.getCgpa() != null) student.setCgpa(request.getCgpa());
        if (request.getPhone() != null) student.setPhone(request.getPhone());
        if (request.getResumeUrl() != null) student.setResumeUrl(request.getResumeUrl());

        studentRepository.save(student);

        StudentProfileResponse response = new StudentProfileResponse();
        response.setStudentId(student.getStudentId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setDepartment(student.getDepartment());
        response.setCgpa(student.getCgpa());
        response.setPhone(student.getPhone());
        response.setResumeUrl(student.getResumeUrl());
        return ResponseEntity.ok(response);
    }

    private void requireStudentRole(Authentication authentication) {
        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
        if (!isStudent) {
            throw new IllegalArgumentException("Student role required");
        }
    }
}