package com.example.notification_service.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Notification {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//primary key

    private long userID;
    private String message;
    private LocalDateTime timestamp;
    private String alertType;

}
