package com.jaden_2.solar.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaden_2.solar.backend.entities.inventory.Battery;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(schema = "solar_inventory")
@Entity
public class BatterySpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToMany(mappedBy = "username")
    private User creator;

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
