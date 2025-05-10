package com.example.health_data_service.event;

import com.example.health_data_service.view.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.example.health_data_service.config.RabbitMQConfig;

@Service
@RequiredArgsConstructor
public class MeasurementEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishMeasurement(MeasurementDto measurement) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.MEASUREMENT_EXCHANGE,
            RabbitMQConfig.MEASUREMENT_ROUTING_KEY,
            measurement
        );
    }
}
