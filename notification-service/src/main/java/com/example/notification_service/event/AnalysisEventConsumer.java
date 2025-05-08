package com.example.notification_service.event;

import com.example.analysis_service.view.AnalysisDto;
import com.example.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisEventConsumer {
    
    private final NotificationService notificationService;
    private static final String ANALYSIS_QUEUE = "analysis.queue";

    @RabbitListener(queues = ANALYSIS_QUEUE)
    public void receiveAnalysis(AnalysisDto analysis) {
        if (analysis.isDanger() || analysis.getIllnessPrediction() != null) {
            notificationService.sendNotification(analysis);
        }
    }
} 