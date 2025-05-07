package com.example.health_data_service.view;

import com.example.health_data_service.controller.MeasurementCommandService;
import com.example.health_data_service.controller.MeasurementQueryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementEventPublisher publisher;
    
    private final MeasurementCommandService commandService;
    private final MeasurementQueryService queryService;

    public MeasurementController(MeasurementCommandService commandService,
                                 MeasurementQueryService queryService) {
        this.publisher = publisher;
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<Void> submit(@RequestBody MeasurementDto dto) {
        publisher.sendMeasurement(dto);
        return ResponseEntity.ok().build();
    }

    public void create(@RequestBody MeasurementDto dto) {
        commandService.saveMeasurement(dto);
    }

    @GetMapping
    public List<MeasurementDto> getAll() {
        return queryService.getAllMeasurements();
    }
}
