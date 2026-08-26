package com.renault.telemetry.repository;

import com.renault.telemetry.model.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
}
