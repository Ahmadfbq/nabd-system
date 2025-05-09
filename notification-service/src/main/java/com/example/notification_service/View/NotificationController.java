package com.example.notification_service.view;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification_service.controller.NotificationService;

@RestController
@RequestMapping("/api/alert")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService service;

}
