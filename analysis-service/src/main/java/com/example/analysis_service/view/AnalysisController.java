package com.example.analysis_service.view;

import com.example.analysis_service.controller.AnalysisService;
import com.example.health_data_service.view.MeasurementDto;
import com.example.health_data_service.model.Measurement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    private final AnalysisService service;

    @PostMapping
    public ResponseEntity<AnalysisDto> analyze(@RequestBody @Validated MeasurementDto measurementDto) {
        try {
            logger.info("Received analysis request for measurement: {}", measurementDto);
            
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
            
            AnalysisDto result = service.analyze(dto);
            logger.info("Analysis completed successfully");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error performing analysis", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Add endpoint to analyze multiple measurements
    @PostMapping("/batch")
    public ResponseEntity<AnalysisDto> analyzeBatch(@RequestBody @Validated List<MeasurementDto> measurementDtos) {
        try {
            logger.info("Received batch analysis request for {} measurements", measurementDtos.size());
            
            AnalysisDto dto = new AnalysisDto();
            List<Measurement> measurements = measurementDtos.stream()
                .map(measurementDto -> Measurement.builder()
                    .temperature(measurementDto.getTemperature())
                    .heartRate(measurementDto.getHeartRate())
                    .bloodPressure(measurementDto.getBloodPressure())
                    .bloodSugar(measurementDto.getBloodSugar())
                    .oxygenLevel(measurementDto.getOxygenLevel())
                    .timestamp(measurementDto.getTimestamp())
                    .build())
                .toList();
            
            dto.setMeasurements(measurements);
            
            AnalysisDto result = service.analyze(dto);
            logger.info("Batch analysis completed successfully");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Error performing batch analysis", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}