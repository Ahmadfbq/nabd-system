package com.example.health_data_service.query.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity 
@Getter 
@Setter 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor
@Table(name = "measurements_query")
public class MeasurementView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double value;
    private LocalDateTime timestamp;
    private String deviceId;
    
    // Additional fields for query optimization
    private String status;
    private String analysisResult;
    private LocalDateTime lastUpdated;
} 