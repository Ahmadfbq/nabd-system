package com.example.health_data_service.view;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MeasurementDto {
    private int deviceId;
    private double temperature;
    private int heartRate;
    private int bloodPressure;
    private int bloodSugar;
    private int oxygenLevel;
    private LocalDateTime timestamp;
}
