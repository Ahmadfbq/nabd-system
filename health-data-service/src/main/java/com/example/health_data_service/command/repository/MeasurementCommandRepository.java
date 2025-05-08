package com.example.health_data_service.command.repository;

import com.example.health_data_service.command.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementCommandRepository extends JpaRepository<Measurement, Long> {
} 