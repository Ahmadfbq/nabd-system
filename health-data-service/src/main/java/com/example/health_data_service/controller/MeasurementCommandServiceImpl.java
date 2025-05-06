package com.example.health_data_service.controller;

import com.example.health_data_service.view.MeasurementDto;
import com.example.health_data_service.model.Measurement;
import com.example.health_data_service.model.MeasurementMapper;
import com.example.health_data_service.model.MeasurementRepository;
import com.example.health_data_service.event.MeasurementEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MeasurementCommandServiceImpl implements MeasurementCommandService {

    private final MeasurementRepository repository;
    private final MeasurementMapper mapper;
    private final MeasurementEventPublisher eventPublisher;

    public MeasurementCommandServiceImpl(MeasurementRepository repository,
                                         MeasurementMapper mapper,
                                         MeasurementEventPublisher eventPublisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void saveMeasurement(MeasurementDto dto) {
        Measurement measurement = mapper.toEntity(dto);
        repository.save(measurement);
        eventPublisher.publishMeasurementCreated(measurement);
    }

}
