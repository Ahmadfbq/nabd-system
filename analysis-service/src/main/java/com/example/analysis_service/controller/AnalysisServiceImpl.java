package com.example.analysis_service.controller;

import com.example.analysis_service.view.AnalysisDto;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisRepository;
import com.example.analysis_service.controller.AnalysisService;
import com.example.health_data_service.model.Measurement;
import lombok.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static java.util.stream.Collectors.*;


@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository repository;
    private final AnalysisMapper mapper;

    @Override
    public AnalysisDto analyze(AnalysisDto dto) {
        // populate timestamp
        dto.setTimestamp(LocalDateTime.now());
        Analysis entity = mapper.toEntity(dto);

        // perform analysis logic
        checkForDanger(entity);
        detectIllness(entity);

        // persist
        Analysis saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public void checkForDanger(Analysis a) {
        float score = 0f;
        for (Measurement m : a.getMeasurements()) {
            // Check heart rate
            if (m.getHeartRate() < 60 || m.getHeartRate() > 100) {
                score++;
            }
            
            // Check oxygen level
            if (m.getOxygenLevel() < 95) {
                score++;
            }
            
            // Check blood pressure
            if (m.getBloodPressure() >= 130) {
                score++;
            }
            
            // Check blood sugar
            if (m.getBloodSugar() >= 126) {
                score++;
            }
            
            // Check temperature
            if (m.getTemperature() > 38) {
                score++;
            }
        }
        a.setAbnormalityScore(score);
        a.setDanger(score > 0);
        a.setResult(a.isDanger() ? "Danger detected" : "No danger");
    }

    public void detectIllness(Analysis a) {
        boolean diabetes = a.getMeasurements().stream()
                .allMatch(m -> m.getBloodSugar() >= 126);

        boolean hypertension = a.getMeasurements().stream()
                .allMatch(m -> m.getBloodPressure() >= 130);

        boolean fever = a.getMeasurements().stream()
                .anyMatch(m -> m.getTemperature() > 38 && m.getTimestamp().isAfter(LocalDateTime.now().minusHours(2)));

        String prediction;
        if (diabetes) {
            prediction = "Diabetes risk";
        } else if (hypertension) {
            prediction = "Hypertension risk";
        } else if (fever) {
            prediction = "Fever detected";
        } else {
            prediction = "No illness detected";
        }
        a.setIllnessPrediction(prediction);
        a.setDescription("Illness detection based on trends");
        a.setResult(prediction);
    }
}

