package com.example.health_data_service.event;

import com.example.health_data_service.model.Measurement;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Service
public class MeasurementEventPublisher {
    @Value("${app.queue.measurement}")
    private String queue;

    private final RabbitTemplate rabbitTemplate;

    public MeasurementEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMeasurement(MeasurementDto measurementDto) {
        rabbitTemplate.convertAndSend(queue, measurementDto);
    }
}