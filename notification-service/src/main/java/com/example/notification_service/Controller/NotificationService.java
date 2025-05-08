package com.example.notification_service.Controller;

public interface NotificationService {
    void sendNotification(String message);
    void callEmergency(String emergencyDetails);
}
