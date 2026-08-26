package com.renault.telemetry.controller;

import com.renault.telemetry.model.TelemetryRequest;
import com.renault.telemetry.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TelemetryRequest request) {
        service.receive(request);
        return ResponseEntity.accepted().build();
    }
}
