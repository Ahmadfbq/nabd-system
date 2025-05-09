package com.example.analysis_service.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.health_data_service.model.Measurement;

public class SleepPatternTest {
    @Test
    public void testDurationCalculation() {
        // Given
        LocalDateTime startTime = LocalDateTime.now().minusHours(8);
        LocalDateTime endTime = LocalDateTime.now();
        
        SleepPattern sleepPattern = new SleepPattern();
        sleepPattern.setStartTime(startTime);
        sleepPattern.setEndTime(endTime);
        
        List<Measurement> measurements = new ArrayList<>();
        Measurement startMeasurement = Measurement.builder()
            .timestamp(startTime)
            .heartRate(55)
            .build();
        measurements.add(startMeasurement);
        
        Measurement endMeasurement = Measurement.builder()
            .timestamp(endTime)
            .heartRate(75)
            .build();
        measurements.add(endMeasurement);
        
        // When
        sleepPattern.getDuration(measurements);
        
        // Then
        assertNotNull(sleepPattern.getStartTime());
        assertNotNull(sleepPattern.getEndTime());
        assertEquals(startTime, sleepPattern.getStartTime());
        assertEquals(endTime, sleepPattern.getEndTime());
    }
}