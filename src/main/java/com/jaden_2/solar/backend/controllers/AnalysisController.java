package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.DTOs.EstimatorRequest;
import com.jaden_2.solar.backend.entities.SystemReport;
import com.jaden_2.solar.backend.services.SystemAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AnalysisController {

    @Autowired
    SystemAnalysis analysis;
    @PostMapping
    SystemReport generateReport (@RequestBody EstimatorRequest estimate){
        return analysis.analyseSystem(estimate);
    }
}
