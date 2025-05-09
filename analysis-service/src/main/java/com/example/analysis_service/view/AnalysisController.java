package com.example.analysis_service.view;

import com.example.analysis_service.controller.AnalysisService;
import com.example.health_data_service.view.MeasurementDto;
import com.example.health_data_service.model.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService service;

    @PostMapping
    public ResponseEntity<AnalysisDto> analyze(@RequestBody MeasurementDto measurementDto) {
        AnalysisDto dto = new AnalysisDto();
        Measurement measurement = Measurement.builder()
            .temperature(measurementDto.getTemperature())
            .heartRate(measurementDto.getHeartRate())
            .bloodPressure(measurementDto.getBloodPressure())
            .bloodSugar(measurementDto.getBloodSugar())
            .oxygenLevel(measurementDto.getOxygenLevel())
            .timestamp(measurementDto.getTimestamp())
            .build();
        dto.setMeasurements(List.of(measurement));
        return ResponseEntity.ok(service.analyze(dto));
    }
} 