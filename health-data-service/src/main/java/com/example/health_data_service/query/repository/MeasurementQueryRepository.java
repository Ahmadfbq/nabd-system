package com.example.health_data_service.query.repository;

import com.example.health_data_service.query.model.MeasurementView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MeasurementQueryRepository extends JpaRepository<MeasurementView, Long> {
    List<MeasurementView> findByDeviceId(String deviceId);
    List<MeasurementView> findByType(String type);
    List<MeasurementView> findByStatus(String status);
} 