package com.campushiring.repository;

import com.campushiring.entity.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Integer> {
    List<InternshipApplication> findByStudentId(Integer studentId);
    List<InternshipApplication> findByInternshipId(Integer internshipId);
    Optional<InternshipApplication> findByStudentIdAndInternshipId(Integer studentId, Integer internshipId);
}