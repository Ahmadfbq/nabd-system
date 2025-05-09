package com.example.analysis_service.view;

import com.example.analysis_service.model.SleepPattern;
import com.example.health_data_service.model.Measurement;
import com.example.analysis_service.controller.SleepPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sleep")
public class SleepPatternController {

    @Autowired
    private SleepPatternService sleepPatternService;

    @PostMapping("/analyze")
    public SleepPattern analyzeSleep(@RequestParam("userID") int userID,
                                     @RequestBody List<Measurement> measurements) {
        return sleepPatternService.analyzeSleep(userID, measurements);
    }
}