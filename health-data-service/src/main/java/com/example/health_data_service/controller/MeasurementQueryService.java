package com.example.health_data_service.controller;

import com.example.health_data_service.view.MeasurementDto;
import java.util.List;

public interface MeasurementQueryService {
	List<MeasurementDto> getAllMeasurements();
}
