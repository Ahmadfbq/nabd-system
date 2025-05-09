package com.example.analysis_service.view;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class MeasurementDto {
    private int deviceId;
    private double temperature;
    private int heartRate;
    private int bloodPressure;
    private int bloodSugar;
    private int oxygenLevel;
    private LocalDateTime timestamp;
}
