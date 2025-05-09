package com.example.analysis_service.controller;

import com.example.analysis_service.model.SleepPattern;
import com.example.health_data_service.model.Measurement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SleepPatternService {

    public SleepPattern analyzeSleep(int userID, List<Measurement> measurements) {
        SleepPattern sleep = new SleepPattern(userID, LocalDateTime.now().minusHours(7), LocalDateTime.now());
        sleep.getDuration(measurements);
        return sleep;
    }
}