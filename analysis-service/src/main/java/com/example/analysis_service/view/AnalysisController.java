package com.example.analysis_service.view;

import com.example.analysis_service.view.AnalysisDto;
import com.example.analysis_service.controller.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService service;

    @PostMapping
    public AnalysisDto analyze(@RequestBody AnalysisDto dto) {
        return service.analyze(dto);
    }
}

