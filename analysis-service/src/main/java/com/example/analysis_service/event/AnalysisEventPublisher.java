package com.example.analysis_service.event;

import com.example.analysis_service.view.AnalysisDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    private static final String ANALYSIS_QUEUE = "analysis.queue";

    public void publishAnalysis(AnalysisDto analysis) {
        rabbitTemplate.convertAndSend(ANALYSIS_QUEUE, analysis);
    }
} 