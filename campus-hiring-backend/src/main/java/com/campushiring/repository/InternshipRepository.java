package com.campushiring.repository;

import com.campushiring.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Integer> {
    List<Internship> findByCompanyId(Long companyId);
}