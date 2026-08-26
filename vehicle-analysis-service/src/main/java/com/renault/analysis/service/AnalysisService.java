package com.renault.analysis.service;

import com.renault.analysis.model.*;
import com.renault.analysis.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final HealthScoreService scoreService;
    private final RecallProvider recallProvider;
    private final HealthReportRepository reportRepository;
    private final AlertRepository alertRepository;

    public void analyze(Telemetry telemetry) {
        boolean recall = recallProvider.hasRecall(telemetry.getVin());
        int score = scoreService.calculate(telemetry, recall);

        reportRepository.save(HealthReport.builder()
                .vin(telemetry.getVin())
                .score(score)
                .recall(recall)
                .build());

        if (telemetry.getTirePressure() < 2.0 || telemetry.getTirePressure() > 2.5) {
            createAlert(telemetry.getVin(), "Pression des pneus anormale");
        }

        if (telemetry.getBatteryVoltage() < 12.0) {
            createAlert(telemetry.getVin(), "Batterie faible");
        }

        if (telemetry.getEngineTemperature() > 110) {
            createAlert(telemetry.getVin(), "Température moteur élevée");
        }

        if (recall) {
            createAlert(telemetry.getVin(), "Campagne de rappel active");
        }

        if (telemetry.getErrorCodes() != null) {
            telemetry.getErrorCodes().forEach(code ->
                    createAlert(telemetry.getVin(), "Code erreur: " + code));
        }
    }

    private void createAlert(String vin, String message) {
        alertRepository.save(Alert.builder().vin(vin).message(message).build());
    }

    public HealthReport getReport(String vin) {
        return reportRepository.findById(vin).orElseThrow();
    }

    public List<Alert> getAlerts(String vin) {
        return alertRepository.findByVin(vin);
    }
}
