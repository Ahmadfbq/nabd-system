package com.example.analysis_service.service;

import com.example.analysis_service.controller.AnalysisServiceImpl;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisRepository;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.health_data_service.model.Measurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisServiceTest {

    private AnalysisServiceImpl analysisService;
    
    @Mock
    private AnalysisRepository repository;
    
    @Mock
    private AnalysisMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        analysisService = new AnalysisServiceImpl(repository, mapper);
    }

    @Test
    void testCheckForDanger_NormalHeartRate() {
        // Given
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement measurement = new Measurement();
        measurement.setHeartRate(75);
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
        measurement.setHeartRate(120); // High heart rate
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
        measurement.setBloodSugar(130); // High blood sugar
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
        measurement.setBloodPressure(140); // High blood pressure
        measurement.setTimestamp(LocalDateTime.now());
        measurements.add(measurement);
        
        analysis.setMeasurements(measurements);

        // When
        analysisService.detectIllness(analysis);

        // Then
        assertEquals("Hypertension risk", analysis.getIllnessPrediction());
    }
} 