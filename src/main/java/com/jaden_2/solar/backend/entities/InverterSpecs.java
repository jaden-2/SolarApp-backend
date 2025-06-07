package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.inventory.Inverter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToMany(mappedBy = "username")
    private User creatorFor;

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
