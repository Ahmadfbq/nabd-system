package com.example.health_data_service.view;

import com.example.health_data_service.controller.MeasurementCommandService;
import com.example.health_data_service.controller.MeasurementQueryService;
import com.example.health_data_service.event.MeasurementEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementEventPublisher publisher;
    private final MeasurementCommandService commandService;
    private final MeasurementQueryService queryService;

    @PostMapping
    public ResponseEntity<Void> submit(@RequestBody MeasurementDto dto) {
        // First save the measurement
        commandService.saveMeasurement(dto);
        // Then publish it to the analysis service
        publisher.publishMeasurement(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<MeasurementDto> getAll() {
        return queryService.getAllMeasurements();
    }
}
