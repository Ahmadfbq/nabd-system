package com.example.analysis_service.controller;

import com.example.analysis_service.view.AnalysisDto;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisRepository;
import com.example.analysis_service.controller.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import static java.util.stream.Collectors.*;


@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository repository;
    private final AnalysisMapper mapper;

    @Override
    public AnalysisDto analyze(AnalysisDto dto) {
        // populate timestamp
        dto.setTimestamp(LocalDateTime.now());
        Analysis entity = mapper.toEntity(dto);

        // perform analysis logic
        checkForDanger(entity);
        detectIllness(entity);

        // persist
        Analysis saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    private void checkForDanger(Analysis a) {
        float score = 0f;
        for (var m : a.getMeasurements()) {
            switch (m.getType()) {
                case "heartRate" -> {
                    if (m.getValue() < 60 || m.getValue() > 100) score++;
                }
                case "oxygenLevel" -> {
                    if (m.getValue() < 95) score++;
                }
                case "bloodPressure" -> {
                    if (m.getValue() >= 130) score++;
                }
                case "bloodSugar" -> {
                    if (m.getValue() >= 126) score++;
                }
                case "temperature" -> {
                    if (m.getValue() > 38) score++;
                }
            }
        }
        a.setAbnormalityScore(score);
        a.setDanger(score > 0);
        a.setResult(a.isDanger() ? "Danger detected" : "No danger");
    }

    private void detectIllness(Analysis a) {
        boolean diabetes = a.getMeasurements().stream()
            .filter(m -> m.getType().equals("bloodSugar"))
            .allMatch(m -> m.getValue() >= 126);

        boolean hypertension = a.getMeasurements().stream()
            .filter(m -> m.getType().equals("bloodPressure"))
            .allMatch(m -> m.getValue() >= 130);

        boolean fever = a.getMeasurements().stream()
            .filter(m -> m.getType().equals("temperature"))
            .anyMatch(m -> m.getValue() > 38 && m.getTimestamp().isAfter(LocalDateTime.now().minusHours(2)));

        String prediction;
        if (diabetes) {
            prediction = "Diabetes risk";
        } else if (hypertension) {
            prediction = "Hypertension risk";
        } else if (fever) {
            prediction = "Fever detected";
        } else {
            prediction = "No illness detected";
        }
        a.setIllnessPrediction(prediction);
        a.setDescription("Illness detection based on trends");
        a.setResult(prediction);
    }
}

