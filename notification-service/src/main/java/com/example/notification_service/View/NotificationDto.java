package com.example.notification_service.view;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationDto {
    private Long id;
    private long userID; // Foreign key from User
    private String message;
    private LocalDateTime timestamp;
    private String alertType; //empty for now
}