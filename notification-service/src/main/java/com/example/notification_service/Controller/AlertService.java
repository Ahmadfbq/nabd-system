package com.example.notification_service.Controller;

public interface AlertService {
    void sendNotification(String message);
    void callEmergency(String emergencyDetails);
}
