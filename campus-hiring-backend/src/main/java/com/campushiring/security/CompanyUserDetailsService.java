package com.campushiring.security;

import com.campushiring.entity.Company;
import com.campushiring.repository.CompanyRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompanyUserDetailsService implements UserDetailsService {

    private final CompanyRepository companyRepository;

    public CompanyUserDetailsService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Company company = companyRepository.findByEmail(email);
        if (company == null) {
            throw new UsernameNotFoundException("Company not found with email: " + email);
        }
        return User.withUsername(company.getEmail())
                .password(company.getPassword())
                .authorities("ROLE_COMPANY")
                .build();
    }
}