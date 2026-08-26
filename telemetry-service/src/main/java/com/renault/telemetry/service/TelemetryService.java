package com.renault.telemetry.service;

import com.renault.telemetry.model.Telemetry;
import com.renault.telemetry.model.TelemetryRequest;
import com.renault.telemetry.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class TelemetryService {

    private final TelemetryRepository repository;

    @Value("${analysis.url}")
    private String analysisUrl;

    public void receive(TelemetryRequest request) {
        Telemetry telemetry = Telemetry.builder()
                .vin(request.getVin())
                .mileage(request.getMileage())
                .tirePressure(request.getTirePressure())
                .batteryVoltage(request.getBatteryVoltage())
                .engineTemperature(request.getEngineTemperature())
                .fuelLevel(request.getFuelLevel())
                .errorCodes(request.getErrorCodes())
                .build();

        Telemetry saved = repository.save(telemetry);

        RestClient.create(analysisUrl)
                .post()
                .uri("/internal/analyze")
                .body(saved)
                .retrieve()
                .toBodilessEntity();
    }
}
