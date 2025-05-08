package com.example.health_data_service.service;

import com.example.health_data_service.model.Measurement;
import com.example.health_data_service.model.MeasurementRepository;
import com.example.health_data_service.view.MeasurementDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeasurementServiceTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @InjectMocks
    private MeasurementService measurementService;

    @Test
    void testCreateMeasurement() {
        // Given
        MeasurementDto dto = new MeasurementDto();
        dto.setType("heartRate");
        dto.setValue(75.0);
        dto.setTimestamp(LocalDateTime.now());

        Measurement savedMeasurement = new Measurement();
        savedMeasurement.setId(1L);
        savedMeasurement.setType(dto.getType());
        savedMeasurement.setValue(dto.getValue());
        savedMeasurement.setTimestamp(dto.getTimestamp());

        when(measurementRepository.save(any(Measurement.class))).thenReturn(savedMeasurement);

        // When
        MeasurementDto result = measurementService.createMeasurement(dto);

        // Then
        assertNotNull(result);
        assertEquals("heartRate", result.getType());
        assertEquals(75.0, result.getValue());
    }

    @Test
    void testGetMeasurement() {
        // Given
        Measurement measurement = new Measurement();
        measurement.setId(1L);
        measurement.setType("heartRate");
        measurement.setValue(75.0);
        measurement.setTimestamp(LocalDateTime.now());

        when(measurementRepository.findById(1L)).thenReturn(Optional.of(measurement));

        // When
        MeasurementDto result = measurementService.getMeasurement(1L);

        // Then
        assertNotNull(result);
        assertEquals("heartRate", result.getType());
        assertEquals(75.0, result.getValue());
    }

    @Test
    void testGetMeasurementNotFound() {
        // Given
        when(measurementRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> measurementService.getMeasurement(999L));
    }
} 