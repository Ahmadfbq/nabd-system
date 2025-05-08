package com.example.user_service.event;

import com.example.analysis_service.view.AnalysisDto;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisEventConsumer {
    
    private final UserService userService;
    private static final String ANALYSIS_QUEUE = "analysis.queue";

    @RabbitListener(queues = ANALYSIS_QUEUE)
    public void receiveAnalysis(AnalysisDto analysis) {
        // Update user's health status based on analysis
        userService.updateUserHealthStatus(analysis);
    }
} 