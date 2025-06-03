package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.Inverter;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sylvanus Jedidiah
 * @version 1.0
 * @since 2025
 * @implNote InverterSpecs allows me dynamically create specification for the inverter
 * even when such specification does not exist in db*/
@Data
@NoArgsConstructor
public class InverterSpecs {
    private String name;
    private String model;
    private double capacityKva;
    private Integer systemVoltage;
    private String type = "Sine";
    private Double calculatedInverterCapacityKva;
    private Configuration<Integer, Integer> configuration;
    public InverterSpecs(Inverter inverter, double recommendation, Configuration<Integer, Integer> config){
        this.name= inverter.getName();
        this.model = inverter.getModel();
        this.capacityKva = inverter.getCapacity();
        this.systemVoltage = inverter.getSystemVoltage();
        this.type = inverter.getType();
        configuration = config;
        calculatedInverterCapacityKva = recommendation;
    }

}
