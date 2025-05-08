package com.example.notification_service.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alert")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService service;

}
