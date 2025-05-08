package com.example.notification_service.Model;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlertRepository extends JpaRepository<Alert, Long> {
}
