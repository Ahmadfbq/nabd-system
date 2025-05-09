package com.example.analysis_service.service;

import com.example.analysis_service.controller.SleepPatternService;
import com.example.analysis_service.model.SleepPattern;
import com.example.health_data_service.model.Measurement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class SleepPatternServiceTest {
    private SleepPatternService sleepPatternService;
    
    @BeforeEach
    public void setUp() {
        sleepPatternService = new SleepPatternService();
    }
    
    @Test
    public void testAnalyzeSleep_ValidMeasurements() {
        // Given
        int userID = 1;
        List<Measurement> measurements = new ArrayList<>();
        
        Measurement startMeasurement = Measurement.builder()
            .timestamp(LocalDateTime.now().minusHours(7))
            .heartRate(55)
            .build();
        measurements.add(startMeasurement);
        
        Measurement endMeasurement = Measurement.builder()
            .timestamp(LocalDateTime.now())
            .heartRate(75)
            .build();
        measurements.add(endMeasurement);
        
        // When
        SleepPattern result = sleepPatternService.analyzeSleep(userID, measurements);
        
        // Then
        assertNotNull(result);
        assertNotNull(result.getStartTime());
        assertNotNull(result.getEndTime());
    }
}
