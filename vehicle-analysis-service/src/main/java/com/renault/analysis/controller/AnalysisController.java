package com.renault.analysis.controller;

import com.renault.analysis.model.*;
import com.renault.analysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService service;

    @PostMapping("/internal/analyze")
    public ResponseEntity<Void> analyze(@RequestBody Telemetry telemetry) {
        service.analyze(telemetry);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/vehicles/{vin}/health-report")
    public HealthReport report(@PathVariable String vin) {
        return service.getReport(vin);
    }

    @GetMapping("/api/v1/vehicles/{vin}/alerts")
    public List<Alert> alerts(@PathVariable String vin) {
        return service.getAlerts(vin);
    }
}
