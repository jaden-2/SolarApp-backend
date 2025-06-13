package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.DTOs.EstimatorRequest;
import com.jaden_2.solar.backend.DTOs.ReportDTO;
import com.jaden_2.solar.backend.services.SystemAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class SystemReportController {

    @Autowired
    SystemAnalysis analysis;


    @PostMapping
    public ResponseEntity<ReportDTO> generateReport (@RequestBody EstimatorRequest estimate){
        return null;
    }
}
