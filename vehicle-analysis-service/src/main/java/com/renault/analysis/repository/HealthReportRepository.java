package com.renault.analysis.repository;

import com.renault.analysis.model.HealthReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthReportRepository extends JpaRepository<HealthReport, String> {
}
