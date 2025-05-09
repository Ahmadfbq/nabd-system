package com.example.notification_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }

    @Override
    public void callEmergency(String emergencyDetails) {
        System.out.println("Emergency services called with details: " + emergencyDetails);
    }
}
