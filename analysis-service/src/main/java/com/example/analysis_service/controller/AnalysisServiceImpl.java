package com.example.analysis_service.controller;

import com.example.analysis_service.view.AnalysisDto;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisRepository;
import com.example.health_data_service.model.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceImpl.class);
    
    // Health thresholds that could be moved to configuration
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final int MIN_OXYGEN_LEVEL = 95;
    private static final int HIGH_BLOOD_PRESSURE = 130;
    private static final int HIGH_BLOOD_SUGAR = 126;
    private static final float HIGH_TEMPERATURE = 38.0f;

    private final AnalysisRepository repository;
    private final AnalysisMapper mapper;

    @Override
    public AnalysisDto analyze(AnalysisDto dto) {
        if (dto == null) {
            logger.error("Cannot analyze null DTO");
            throw new IllegalArgumentException("Analysis DTO cannot be null");
        }
        
        // populate timestamp
        dto.setTimestamp(LocalDateTime.now());
        Analysis entity = mapper.toEntity(dto);

        // perform analysis logic
        checkForDanger(entity);
        detectIllness(entity);

        try {
            // persist
            Analysis saved = repository.save(entity);
            return mapper.toDto(saved);
        } catch (Exception e) {
            logger.error("Error saving analysis", e);
            throw new RuntimeException("Failed to save analysis", e);
        }
    }

    public void checkForDanger(Analysis a) {
        if (a == null || a.getMeasurements() == null || a.getMeasurements().isEmpty()) {
            logger.warn("No measurements available for danger check");
            return;
        }
        
        float score = 0f;
        for (Measurement m : a.getMeasurements()) {
            // Check heart rate
            if (m.getHeartRate() < MIN_HEART_RATE || m.getHeartRate() > MAX_HEART_RATE) {
                score++;
            }
            
            // Check oxygen level
            if (m.getOxygenLevel() < MIN_OXYGEN_LEVEL) {
                score++;
            }
            
            // Check blood pressure
            if (m.getBloodPressure() >= HIGH_BLOOD_PRESSURE) {
                score++;
            }
            
            // Check blood sugar
            if (m.getBloodSugar() >= HIGH_BLOOD_SUGAR) {
                score++;
            }
            
            // Check temperature
            if (m.getTemperature() > HIGH_TEMPERATURE) {
                score++;
            }
        }
        a.setAbnormalityScore(score);
        a.setDanger(score > 0);
        a.setResult(a.isDanger() ? "Danger detected" : "No danger");
    }

    public void detectIllness(Analysis a) {
        if (a == null || a.getMeasurements() == null || a.getMeasurements().isEmpty()) {
            logger.warn("No measurements available for illness detection");
            return;
        }
        
        List<Measurement> measurements = a.getMeasurements();
        
        // More lenient check - consider risk if any measurement exceeds threshold
        boolean diabetesRisk = measurements.stream()
                .anyMatch(m -> m.getBloodSugar() >= HIGH_BLOOD_SUGAR);

        boolean hypertensionRisk = measurements.stream()
                .anyMatch(m -> m.getBloodPressure() >= HIGH_BLOOD_PRESSURE);

        boolean fever = measurements.stream()
                .anyMatch(m -> m.getTemperature() > HIGH_TEMPERATURE && 
                              m.getTimestamp().isAfter(LocalDateTime.now().minusHours(2)));

        String prediction;
        if (diabetesRisk) {
            prediction = "Diabetes risk";
        } else if (hypertensionRisk) {
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

