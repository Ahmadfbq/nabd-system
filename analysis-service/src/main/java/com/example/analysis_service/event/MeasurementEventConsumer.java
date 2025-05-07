package com.example.analysis_service.event;
import com.example.analysis_service.controller.AnalysisServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MeasurementEventConsumer {
    private final AnalysisServiceImpl analysis;
    @RabbitListener(queues = "${app.queue.easurement}");
    public void receiveMeasurement(MeasurementDto dto) {
        analysis.checkForDanger(dto);
        analysis.detectIllness(dto);
    }
}
