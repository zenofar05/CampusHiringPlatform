package com.campushiring.repository;

import com.campushiring.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByJobId(Long jobId);
    
    @Query("SELECT a FROM Application a JOIN Job j ON a.jobId = j.jobId WHERE j.companyId = ?1")
    List<Application> findByCompanyIdViaJob(Long companyId);
    
    Optional<Application> findByStudentIdAndJobId(Long studentId, Long jobId);
    Optional<Application> findByStudentIdAndInternshipId(Long studentId, Long internshipId);
}