package com.example.analysis_service.event;

import com.example.analysis_service.controller.AnalysisServiceImpl;
import com.example.health_data_service.view.MeasurementDto;
import com.example.analysis_service.model.Analysis;
import com.example.analysis_service.model.AnalysisMapper;
import com.example.analysis_service.model.AnalysisRepository;
import com.example.health_data_service.model.Measurement;
import com.example.analysis_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MeasurementEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(MeasurementEventConsumer.class);
    
    private final AnalysisServiceImpl analysisService;
    private final AnalysisMapper mapper;
    private final AnalysisRepository repository;

    @Autowired
    public MeasurementEventConsumer(AnalysisServiceImpl analysisService, AnalysisMapper mapper, AnalysisRepository repository) {
        this.analysisService = analysisService;
        this.mapper = mapper;
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.MEASUREMENT_QUEUE)
    public void receiveMeasurement(MeasurementDto dto) {
        try {
            if (dto == null) {
                logger.error("Received null measurement DTO");
                return;
            }
            
            logger.info("Received measurement: {}", dto);
            
            Analysis analysis = new Analysis();
            analysis.setTimestamp(LocalDateTime.now());
            
            // Create a measurement for the single metric that has a value
            Measurement measurement = new Measurement();
            measurement.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());
            
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
            analysisService.checkForDanger(analysis);
            analysisService.detectIllness(analysis);
            
            // Save the analysis
            repository.save(analysis);
            logger.info("Analysis saved for measurement event");
        } catch (Exception e) {
            logger.error("Error processing measurement event", e);
        }
    }
}
