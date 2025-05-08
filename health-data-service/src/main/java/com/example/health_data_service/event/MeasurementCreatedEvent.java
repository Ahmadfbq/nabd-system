package com.example.health_data_service.event;

import com.example.health_data_service.model.Measurement;
import org.springframework.context.ApplicationEvent;

public class MeasurementCreatedEvent extends ApplicationEvent {
    public MeasurementCreatedEvent(Measurement measurement) {
        super(measurement);
    }

    public Measurement getMeasurement() {
        return (Measurement) getSource();
    }
}