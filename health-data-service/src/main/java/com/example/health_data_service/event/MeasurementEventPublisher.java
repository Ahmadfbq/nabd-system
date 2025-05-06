package com.example.health_data_service.event;

import com.example.health_data_service.model.Measurement;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MeasurementEventPublisher {

    private final ApplicationEventPublisher publisher;

    public MeasurementEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishMeasurementCreated(Measurement measurement) {
        publisher.publishEvent(new MeasurementCreatedEvent(measurement));
    }
}