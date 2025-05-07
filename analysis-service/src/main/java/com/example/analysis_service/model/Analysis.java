package com.example.analysis_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Transient
    private List<com.example.health_data_service.domain.model.Measurement> measurements;

    private String result;
    private boolean isDanger;
    private float abnormalityScore;
    private String illnessPrediction;
    private String description;
}

