package com.renault.analysis.model;

import lombok.Data;
import java.util.List;

@Data
public class Telemetry {
    private String vin;
    private double mileage;
    private double tirePressure;
    private double batteryVoltage;
    private double engineTemperature;
    private double fuelLevel;
    private List<String> errorCodes;
}
