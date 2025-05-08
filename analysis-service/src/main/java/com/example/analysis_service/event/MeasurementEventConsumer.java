package com.example.analysis_service.event;
import com.example.analysis_service.controller.AnalysisServiceImpl;
import com.example.health_data_service.view.MeasurementDto;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.health_data_service.domain.model.Measurement;
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
        
        // Convert MeasurementDto to Measurement
        Measurement measurement = new Measurement();
        measurement.setType(dto.getType());
        measurement.setValue(dto.getValue());
        measurement.setTimestamp(dto.getTimestamp());
        
        analysis.getMeasurements().add(measurement);
        this.analysis.checkForDanger(analysis);
        this.analysis.detectIllness(analysis);
    }
}
