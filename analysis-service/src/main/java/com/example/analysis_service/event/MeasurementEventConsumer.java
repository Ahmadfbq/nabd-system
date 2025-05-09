package com.example.analysis_service.event;

import com.example.analysis_service.controller.AnalysisServiceImpl;
import com.example.health_data_service.view.MeasurementDto;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.health_data_service.model.Measurement;
import com.example.analysis_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MeasurementEventConsumer {
    private final AnalysisServiceImpl analysis;
    private final AnalysisMapper mapper;

    public MeasurementEventConsumer(AnalysisServiceImpl analysis, AnalysisMapper mapper) {
        this.analysis = analysis;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConfig.MEASUREMENT_QUEUE)
    public void receiveMeasurement(MeasurementDto dto) {
        Analysis analysis = new Analysis();
        analysis.setTimestamp(LocalDateTime.now());
        
        // Create a measurement for the single metric that has a value
        Measurement measurement = new Measurement();
        measurement.setTimestamp(dto.getTimestamp());
        
        // Only set the value for the metric that is being measured
        if (dto.getTemperature() > 0) {
            measurement.setTemperature(dto.getTemperature());
        } else if (dto.getHeartRate() > 0) {
            measurement.setHeartRate(dto.getHeartRate());
        } else if (dto.getBloodPressure() > 0) {
            measurement.setBloodPressure(dto.getBloodPressure());
        } else if (dto.getBloodSugar() > 0) {
            measurement.setBloodSugar(dto.getBloodSugar());
        } else if (dto.getOxygenLevel() > 0) {
            measurement.setOxygenLevel(dto.getOxygenLevel());
        }

        analysis.getMeasurements().add(measurement);
        this.analysis.checkForDanger(analysis);
        this.analysis.detectIllness(analysis);
    }
}
