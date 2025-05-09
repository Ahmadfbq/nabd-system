package com.example.analysis_service.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.example.health_data_service.model.Measurement;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SleepPattern {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public void getDuration(List<Measurement> measurements) {
        if (measurements == null || measurements.isEmpty()) {
            return;
        }

        // Sort measurements by timestamp
        measurements.sort((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        
        // Find the first measurement with low heart rate (indicating sleep start)
        for (Measurement m : measurements) {
            if (m.getHeartRate() < 60) {  // Heart rate typically drops during sleep
                this.startTime = m.getTimestamp();
                break;
            }
        }

        // Find the last measurement with normal heart rate (indicating sleep end)
        for (int i = measurements.size() - 1; i >= 0; i--) {
            Measurement m = measurements.get(i);
            if (m.getHeartRate() >= 60) {  // Heart rate returns to normal when awake
                this.endTime = m.getTimestamp();
                break;
            }
        }
    }
}