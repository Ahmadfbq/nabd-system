package com.example.analysis_service.model;

import com.example.analysis_service.view.AnalysisDto;
import com.example.analysis_service.model.Analysis;
import org.springframework.stereotype.Component;

@Component
public class AnalysisMapper {

    public Analysis toEntity(AnalysisDto dto) {
        return Analysis.builder()
            .id(dto.getId())
            .timestamp(dto.getTimestamp())
            .measurements(dto.getMeasurements())
            .result(dto.getResult())
            .isDanger(dto.isDanger())
            .abnormalityScore(dto.getAbnormalityScore())
            .illnessPrediction(dto.getIllnessPrediction())
            .description(dto.getDescription())
            .build();
    }

    public AnalysisDto toDto(Analysis entity) {
        return AnalysisDto.builder()
            .id(entity.getId())
            .timestamp(entity.getTimestamp())
            .measurements(entity.getMeasurements())
            .result(entity.getResult())
            .danger(entity.isDanger())
            .abnormalityScore(entity.getAbnormalityScore())
            .illnessPrediction(entity.getIllnessPrediction())
            .description(entity.getDescription())
            .build();
    }
}

