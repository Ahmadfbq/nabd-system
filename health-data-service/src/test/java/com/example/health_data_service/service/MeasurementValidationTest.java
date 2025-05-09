package com.example.health_data_service.service;

import com.example.health_data_service.view.MeasurementDto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementValidationTest {

    @Test
    void testValidHeartRate() {
        MeasurementDto dto = new MeasurementDto();
        dto.setHeartRate(75);
        dto.setTimestamp(LocalDateTime.now());

        assertTrue(isValidHeartRate(dto));
    }

    @Test
    void testInvalidHeartRate() {
        MeasurementDto dto = new MeasurementDto();
        dto.setHeartRate(200); // Too high
        dto.setTimestamp(LocalDateTime.now());

        assertFalse(isValidHeartRate(dto));
    }

    @Test
    void testValidBloodPressure() {
        MeasurementDto dto = new MeasurementDto();
        dto.setBloodPressure(120);
        dto.setTimestamp(LocalDateTime.now());

        assertTrue(isValidBloodPressure(dto));
    }

    @Test
    void testInvalidBloodPressure() {
        MeasurementDto dto = new MeasurementDto();
        dto.setBloodPressure(200); // Too high
        dto.setTimestamp(LocalDateTime.now());

        assertFalse(isValidBloodPressure(dto));
    }

    private boolean isValidHeartRate(MeasurementDto dto) {
        return dto.getHeartRate() >= 60 && dto.getHeartRate() <= 100;
    }

    private boolean isValidBloodPressure(MeasurementDto dto) {
        return dto.getBloodPressure() >= 90 && dto.getBloodPressure() <= 140;
    }

    private boolean isValidOxygenLevel(MeasurementDto dto) {
        return dto.getOxygenLevel() >= 95 && dto.getOxygenLevel() <= 100;
    }
} 