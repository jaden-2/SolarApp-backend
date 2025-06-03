package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Battery;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatterySpecs {
    private String brand;
    private Integer batteryCurrentCapacityAh;
    private double batteryVoltage;
    private double batteryEnergyCapacityAh;
    private double requiredBankCapacityAh;
    private BatteryCategory batteryType; // change this to enum after resolving db
    private Configuration<Integer, Integer> configuration;

    public BatterySpecs(Battery battery,double capacity ,Configuration<Integer, Integer> config) {
        this.configuration = config;
        this.brand = battery.getBrand();
        this.requiredBankCapacityAh = capacity;
        this.batteryCurrentCapacityAh = battery.getCurrentCapacity();
        this.batteryEnergyCapacityAh = battery.getEnergyCapacity();
        this.batteryVoltage = battery.getVoltage();
        this.batteryType = battery.getType();
    }

}
