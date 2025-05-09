package com.example.analysis_service.view;

import com.example.analysis_service.controller.SleepPatternService;
import com.example.analysis_service.model.SleepPattern;
import com.example.health_data_service.model.Measurement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class SleepPatternControllerTest {
    @InjectMocks
    private SleepPatternController sleepPatternController;
    
    @Mock
    private SleepPatternService sleepPatternService;
    
    @Test
    public void testAnalyzeSleep() {
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
        
        SleepPattern mockSleepPattern = new SleepPattern();
        mockSleepPattern.setStartTime(LocalDateTime.now().minusHours(7));
        mockSleepPattern.setEndTime(LocalDateTime.now());
        
        when(sleepPatternService.analyzeSleep(userID, measurements)).thenReturn(mockSleepPattern);
        
        // When
        SleepPattern result = sleepPatternController.analyzeSleep(userID, measurements);
        
        // Then
        assertNotNull(result);
        assertNotNull(result.getStartTime());
        assertNotNull(result.getEndTime());
    }
}
