package com.renault.telemetry.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vin;
    private double mileage;
    private double tirePressure;
    private double batteryVoltage;
    private double engineTemperature;
    private double fuelLevel;

    @ElementCollection
    private List<String> errorCodes;
}
