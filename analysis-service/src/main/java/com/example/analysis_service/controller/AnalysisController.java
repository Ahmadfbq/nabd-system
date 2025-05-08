package com.example.analysis_service.view;

import com.example.analysis_service.controller.AnalysisService;
import com.example.health_data_service.view.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService service;

    @PostMapping
    public ResponseEntity<AnalysisDto> analyze(@RequestBody MeasurementDto measurement) {
        AnalysisDto dto = new AnalysisDto();
        dto.setMeasurements(List.of(measurement));
        return ResponseEntity.ok(service.analyze(dto));
    }
} 