package com.example.analysis_service.service;

import com.example.analysis_service.controller.AnalysisServiceImpl;
import com.example.analysis_service.model.Analysis;
import com.example.health_data_service.domain.model.Measurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisServiceTest {

    private AnalysisServiceImpl analysisService;

    @BeforeEach
    void setUp() {
        analysisService = new AnalysisServiceImpl();
    }

    @Test
    void testCheckForDanger_NormalHeartRate() {
        // Given
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement measurement = new Measurement();
        measurement.setType("heartRate");
        measurement.setValue(75.0);
        measurement.setTimestamp(LocalDateTime.now());
        measurements.add(measurement);
        
        analysis.setMeasurements(measurements);

        // When
        analysisService.checkForDanger(analysis);

        // Then
        assertFalse(analysis.isDanger());
        assertEquals(0.0f, analysis.getAbnormalityScore());
    }

    @Test
    void testCheckForDanger_HighHeartRate() {
        // Given
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement measurement = new Measurement();
        measurement.setType("heartRate");
        measurement.setValue(120.0); // High heart rate
        measurement.setTimestamp(LocalDateTime.now());
        measurements.add(measurement);
        
        analysis.setMeasurements(measurements);

        // When
        analysisService.checkForDanger(analysis);

        // Then
        assertTrue(analysis.isDanger());
        assertTrue(analysis.getAbnormalityScore() > 0);
    }

    @Test
    void testDetectIllness_Diabetes() {
        // Given
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement measurement = new Measurement();
        measurement.setType("bloodSugar");
        measurement.setValue(130.0); // High blood sugar
        measurement.setTimestamp(LocalDateTime.now());
        measurements.add(measurement);
        
        analysis.setMeasurements(measurements);

        // When
        analysisService.detectIllness(analysis);

        // Then
        assertEquals("Diabetes risk", analysis.getIllnessPrediction());
    }

    @Test
    void testDetectIllness_Hypertension() {
        // Given
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement measurement = new Measurement();
        measurement.setType("bloodPressure");
        measurement.setValue(140.0); // High blood pressure
        measurement.setTimestamp(LocalDateTime.now());
        measurements.add(measurement);
        
        analysis.setMeasurements(measurements);

        // When
        analysisService.detectIllness(analysis);

        // Then
        assertEquals("Hypertension risk", analysis.getIllnessPrediction());
    }
} 