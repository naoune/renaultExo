package com.renault.analysis.service;

import com.renault.analysis.model.Telemetry;
import org.springframework.stereotype.Service;

@Service
public class HealthScoreService {

    public int calculate(Telemetry t, boolean recall) {
        int score = 100;

        if (t.getMileage() > 100000) score -= 10;
        if (t.getTirePressure() < 2.0 || t.getTirePressure() > 2.5) score -= 10;
        if (t.getBatteryVoltage() < 12.0) score -= 15;
        if (t.getEngineTemperature() > 110) score -= 15;
        if (t.getFuelLevel() < 10) score -= 5;
        if (t.getErrorCodes() != null) score -= t.getErrorCodes().size() * 10;
        if (recall) score -= 20;

        return Math.max(score, 0);
    }
}
