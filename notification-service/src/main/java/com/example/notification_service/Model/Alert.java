package com.example.notification_service.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Alert {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userID;
    private String message;
    private LocalDateTime timestamp;
    private enum alertType{}; //add what type of alert it is

}
