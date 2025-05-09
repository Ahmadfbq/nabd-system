package com.example.notification_service.controller;

public interface NotificationService {
    void sendNotification(String message);
    void callEmergency(String emergencyDetails);
}
