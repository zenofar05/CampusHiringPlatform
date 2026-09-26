package com.campushiring.repository;

import com.campushiring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmail(String email);
    boolean existsByEmail(String email);
}