package com.example.health_data_service.view;

import com.example.health_data_service.service.MeasurementCommandService;
import com.example.health_data_service.service.MeasurementQueryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/measurements")
public class MeasurementController {

    private final MeasurementCommandService commandService;
    private final MeasurementQueryService queryService;

    public MeasurementController(MeasurementCommandService commandService,
                                 MeasurementQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public void create(@RequestBody MeasurementDto dto) {
        commandService.saveMeasurement(dto);
    }

    @GetMapping
    public List<MeasurementDto> getAll() {
        return queryService.getAllMeasurements();
    }
}
