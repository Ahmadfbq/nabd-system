package com.example.analysis_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.health_data_service.model.Measurement;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Transient
    @Builder.Default
    private List<Measurement> measurements = new ArrayList<>();

    private String result;
    // Rename to avoid potential issues with JPA/Jackson serialization
    private boolean danger;  // Changed from isDanger
    private float abnormalityScore;
    private String illnessPrediction;
    private String description;


}

