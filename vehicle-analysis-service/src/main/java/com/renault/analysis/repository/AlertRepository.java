package com.renault.analysis.repository;

import com.renault.analysis.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByVin(String vin);
}
