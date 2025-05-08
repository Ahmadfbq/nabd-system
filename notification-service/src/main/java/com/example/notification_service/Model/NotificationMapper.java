package com.example.notification_service.Model;

import com.example.notification_service.View.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification toEntity(NotificationDto dto) {
        return Notification.builder()
                .id(dto.getId())
                .userID(dto.getUserID())
                .message(dto.getMessage())
                .timestamp(dto.getTimestamp())
                .alertType(dto.getAlertType())
                .build();
    }

    public NotificationDto toDto(Notification entity) {
        return NotificationDto.builder()
                .id(entity.getId())
                .userID(entity.getUserID())
                .message(entity.getMessage())
                .timestamp(entity.getTimestamp())
                .alertType(entity.getAlertType())
                .build();
    }
}
