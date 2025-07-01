package com.jaden_2.solar.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaden_2.solar.backend.entities.inventory.Battery;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class BatterySpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer batteryId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username", name = "creator", nullable = false)
    @JsonIgnore
    private Creator creator;

    private String brand;
    private Integer batteryCurrentCapacityAh;
    private double batteryVoltage;
    private double batteryEnergyCapacityAh;
    private double requiredBankCapacityAh;
    private BatteryCategory batteryType;

    private Configuration configuration;

    public BatterySpecs(Battery battery,double capacity ,Configuration config) {
        this.configuration = config;
        this.brand = battery.getBrand();
        this.requiredBankCapacityAh = capacity;
        this.batteryCurrentCapacityAh = battery.getCurrentCapacity();
        this.batteryEnergyCapacityAh = battery.getEnergyCapacity();
        this.batteryVoltage = battery.getVoltage();
        this.batteryType = battery.getType();
    }

}
