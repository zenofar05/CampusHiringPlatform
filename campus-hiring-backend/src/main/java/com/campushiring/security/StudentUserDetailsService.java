package com.campushiring.security;

import com.campushiring.entity.Student;
import com.campushiring.repository.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public StudentUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("Student not found with email: " + email);
        }
        return User.withUsername(student.getEmail())
                .password(student.getPassword())
                .authorities("ROLE_STUDENT")
                .build();
    }
}