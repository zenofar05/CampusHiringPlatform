package com.campushiring.repository;

import com.campushiring.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByStudentId(Integer studentId);
    List<Notification> findByStudentIdOrderByCreatedAtDesc(Integer studentId);
}