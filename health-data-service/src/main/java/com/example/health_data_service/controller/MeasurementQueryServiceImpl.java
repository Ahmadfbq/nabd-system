package com.example.health_data_service.controller;

import com.example.health_data_service.view.MeasurementDto;
import com.example.health_data_service.model.Measurement;
import com.example.health_data_service.model.MeasurementMapper;
import com.example.health_data_service.model.MeasurementRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementQueryServiceImpl implements MeasurementQueryService {

    private final MeasurementRepository repository;
    private final MeasurementMapper mapper;

    public MeasurementQueryServiceImpl(MeasurementRepository repository, MeasurementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MeasurementDto> getAllMeasurements() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
