package com.renault.analysis.service;

import com.renault.analysis.model.Telemetry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthScoreServiceTest {

    private final HealthScoreService service = new HealthScoreService();

    @Test
    void shouldReturn100ForHealthyVehicle() {
        Telemetry t = telemetry(50000, 2.2, 12.6, 90, 50, List.of());
        assertEquals(100, service.calculate(t, false));
    }

    @Test
    void shouldApplyAllPenalties() {
        Telemetry t = telemetry(150000, 1.8, 11.5, 120, 5, List.of("P0300", "P0420"));
        assertEquals(15, service.calculate(t, true));
    }

    private Telemetry telemetry(double mileage, double pressure, double battery,
                                double temperature, double fuel, List<String> errors) {
        Telemetry t = new Telemetry();
        t.setVin("VIN1");
        t.setMileage(mileage);
        t.setTirePressure(pressure);
        t.setBatteryVoltage(battery);
        t.setEngineTemperature(temperature);
        t.setFuelLevel(fuel);
        t.setErrorCodes(errors);
        return t;
    }
}
