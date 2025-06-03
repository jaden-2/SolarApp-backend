package com.jaden_2.solar.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "solar_inventory", name = "controllers")
@Data
@NoArgsConstructor
public class ChargeController {
    @Id
    private Integer id;
    private String brand;
    private String model;
    private String type;
    private Integer maxChargeCurrent;
    private Integer minVoltage;
    private Integer maxVoltage;
    private Integer maxPower;
    private Integer systemVoltage;
}
