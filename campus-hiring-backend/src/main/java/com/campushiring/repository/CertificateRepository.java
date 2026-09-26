package com.campushiring.repository;

import com.campushiring.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
    List<Certificate> findByStudentId(Long studentId);
    List<Certificate> findByCompanyId(Integer companyId);
}