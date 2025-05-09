package com.example.userService.event;

import com.example.notification_service.view.NotificationDto;
import com.example.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationEventConsumer {
    
    private final UserService userService;
    private static final String NOTIFICATION_QUEUE = "notification.queue";

    @RabbitListener(queues = NOTIFICATION_QUEUE)
    public void receiveNotification(NotificationDto notification) {
        // Update user's health status based on notification
        userService.updateUserHealthStatus(notification);
    }
} 