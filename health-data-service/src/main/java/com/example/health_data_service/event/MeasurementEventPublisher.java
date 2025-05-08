package com.example.health_data_service.event;

import com.example.health_data_service.view.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MeasurementEventPublisher {
    
    private final RestTemplate restTemplate;
    private static final String ANALYSIS_SERVICE_URL = "http://localhost:8082/api/analysis";

    public void publishMeasurement(MeasurementDto measurement) {
        // For now, directly call the analysis service
        restTemplate.postForEntity(ANALYSIS_SERVICE_URL, measurement, Void.class);
    }
}