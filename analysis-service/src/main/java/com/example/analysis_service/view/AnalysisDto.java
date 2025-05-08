package com.example.analysis_service.view;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AnalysisDto {
    private Long id;
    private LocalDateTime timestamp;
    private List<com.example.health_data_service.domain.model.Measurement> measurements;
    private String result;
    private boolean danger;
    private float abnormalityScore;
    private String illnessPrediction;
    private String description;
}
