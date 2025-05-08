package com.example.health_data_service.service;

import com.example.health_data_service.view.MeasurementDto;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MeasurementValidationTest {

    @Test
    void testValidHeartRate() {
        MeasurementDto dto = new MeasurementDto();
        dto.setType("heartRate");
        dto.setValue(75.0);
        dto.setTimestamp(LocalDateTime.now());

        assertTrue(isValidMeasurement(dto));
    }

    @Test
    void testInvalidHeartRate() {
        MeasurementDto dto = new MeasurementDto();
        dto.setType("heartRate");
        dto.setValue(200.0); // Too high
        dto.setTimestamp(LocalDateTime.now());

        assertFalse(isValidMeasurement(dto));
    }

    @Test
    void testValidBloodPressure() {
        MeasurementDto dto = new MeasurementDto();
        dto.setType("bloodPressure");
        dto.setValue(120.0);
        dto.setTimestamp(LocalDateTime.now());

        assertTrue(isValidMeasurement(dto));
    }

    @Test
    void testInvalidBloodPressure() {
        MeasurementDto dto = new MeasurementDto();
        dto.setType("bloodPressure");
        dto.setValue(200.0); // Too high
        dto.setTimestamp(LocalDateTime.now());

        assertFalse(isValidMeasurement(dto));
    }

    private boolean isValidMeasurement(MeasurementDto dto) {
        if (dto.getType() == null || dto.getValue() == null) {
            return false;
        }

        switch (dto.getType()) {
            case "heartRate":
                return dto.getValue() >= 60 && dto.getValue() <= 100;
            case "bloodPressure":
                return dto.getValue() >= 90 && dto.getValue() <= 140;
            case "oxygenLevel":
                return dto.getValue() >= 95 && dto.getValue() <= 100;
            default:
                return false;
        }
    }
} 