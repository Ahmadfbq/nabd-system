package com.example.health_data_service.controller;

import com.example.health_data_service.view.MeasurementDto;

public interface MeasurementCommandService {
	void saveMeasurement(MeasurementDto dto);
}
