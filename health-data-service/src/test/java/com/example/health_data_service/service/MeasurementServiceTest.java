package com.example.health_data_service.service;

import com.example.health_data_service.model.Measurement;
import com.example.health_data_service.model.MeasurementRepository;
import com.example.health_data_service.view.MeasurementDto;
import com.example.health_data_service.controller.MeasurementCommandService;
import com.example.health_data_service.controller.MeasurementQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @InjectMocks
    private MeasurementCommandService commandService;
    
    @InjectMocks
    private MeasurementQueryService queryService;

    @Test
    void testSaveMeasurement() {
        // Given
        MeasurementDto dto = new MeasurementDto();
        dto.setDeviceId(1);
        dto.setHeartRate(75);
        dto.setTimestamp(LocalDateTime.now());

        Measurement savedMeasurement = new Measurement();
        savedMeasurement.setId(1L);
        savedMeasurement.setHeartRate(dto.getHeartRate());
        savedMeasurement.setTimestamp(dto.getTimestamp());

        when(measurementRepository.save(any(Measurement.class))).thenReturn(savedMeasurement);

        // When
        commandService.saveMeasurement(dto);

        // Then
        verify(measurementRepository).save(any(Measurement.class));
    }

    @Test
    void testGetAllMeasurements() {
        // Given
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setHeartRate(75);
        measurement.setTimestamp(LocalDateTime.now());

        when(measurementRepository.findAll()).thenReturn(Arrays.asList(measurement));

        // When
        List<MeasurementDto> results = queryService.getAllMeasurements();

        // Then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(75, results.get(0).getHeartRate());
    }
} 