package com.renault.analysis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthReport {
    @Id
    private String vin;
    private int score;
    private boolean recall;
}
