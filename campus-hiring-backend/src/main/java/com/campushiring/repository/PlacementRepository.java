package com.campushiring.repository;

import com.campushiring.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {
    List<Placement> findByStudentId(Long studentId);
    List<Placement> findByCompanyId(Long companyId);
}