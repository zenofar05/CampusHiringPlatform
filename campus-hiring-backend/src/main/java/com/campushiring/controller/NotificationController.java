package com.campushiring.controller;

import com.campushiring.dto.NotificationResponse;
import com.campushiring.entity.Notification;
import com.campushiring.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(Authentication authentication) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());
        List<Notification> notifications = notificationRepository.findByStudentIdOrderByCreatedAtDesc(studentId);
        return ResponseEntity.ok(notifications.stream().map(this::toResponse).toList());
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markAsRead(
            Authentication authentication,
            @PathVariable Integer id) {
        requireStudentRole(authentication);
        Integer studentId = Integer.parseInt(authentication.getName());
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        if (!notification.getStudentId().equals(studentId)) {
            throw new IllegalArgumentException("Not authorized to update this notification");
        }
        notification.setIsRead(true);
        Notification saved = notificationRepository.save(notification);
        return ResponseEntity.ok(toResponse(saved));
    }

    private void requireStudentRole(Authentication authentication) {
        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
        if (!isStudent) {
            throw new IllegalArgumentException("Student role required");
        }
    }

    private NotificationResponse toResponse(Notification n) {
        NotificationResponse response = new NotificationResponse();
        response.setNotificationId(n.getNotificationId());
        response.setStudentId(n.getStudentId());
        response.setMessage(n.getMessage());
        response.setNotificationType(n.getNotificationType());
        response.setIsRead(n.getIsRead());
        response.setCreatedAt(n.getCreatedAt());
        response.setReferenceId(n.getReferenceId());
        response.setReferenceType(n.getReferenceType());
        response.setUpdatedAt(n.getUpdatedAt());
        return response;
    }
}