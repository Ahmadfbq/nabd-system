package com.example.health_data_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private int heartRate;
    private int bloodPressure;
    private int bloodSugar;
    private int oxygenLevel;
    private LocalDateTime timestamp;
}
