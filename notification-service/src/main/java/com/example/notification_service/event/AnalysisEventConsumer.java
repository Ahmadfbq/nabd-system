package com.example.notification_service.event;

import com.example.analysis_service.view.AnalysisDto;
import com.example.notification_service.controller.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import lombok.*;

@Service
@RequiredArgsConstructor
public class AnalysisEventConsumer {
    
    private final NotificationServiceImpl notificationService;
    private static final String ANALYSIS_QUEUE = "analysis.queue";

    @RabbitListener(queues = ANALYSIS_QUEUE)
    public void receiveAnalysis(AnalysisDto analysis) {
        if (analysis.isDanger() || analysis.getIllnessPrediction() != null) {
            StringBuilder message = new StringBuilder();
            
            if (analysis.isDanger()) {
                message.append("⚠️ DANGER ALERT: ");
            }
            
            if (analysis.getIllnessPrediction() != null) {
                message.append("Potential health concern detected: ").append(analysis.getIllnessPrediction());
            }
            
            notificationService.sendNotification(message.toString());   
        }
    }
} 