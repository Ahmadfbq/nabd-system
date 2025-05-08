package com.example.health_data_service.model;

import com.example.health_data_service.view.MeasurementDto;
import org.springframework.stereotype.Component;

@Component
public class MeasurementMapper {

    public Measurement toEntity(MeasurementDto dto) {
        Measurement m = new Measurement();
        m.setTemperature(dto.getTemperature());
        m.setHeartRate(dto.getHeartRate());
        m.setBloodPressure(dto.getBloodPressure());
        m.setBloodSugar(dto.getBloodSugar());
        m.setOxygenLevel(dto.getOxygenLevel());
        m.setTimestamp(dto.getTimestamp());
        return m;
    }

    public MeasurementDto toDto(Measurement entity) {
        MeasurementDto dto = new MeasurementDto();
        dto.setTemperature(entity.getTemperature());
        dto.setHeartRate(entity.getHeartRate());
        dto.setBloodPressure(entity.getBloodPressure());
        dto.setBloodSugar(entity.getBloodSugar());
        dto.setOxygenLevel(entity.getOxygenLevel());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }
}
