package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.inventory.Inverter;
import jakarta.persistence.*;
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
@Entity
@Table(schema = "solar_inventory")
public class InverterSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer inverterId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "username", name = "creator")
    private Creator creator;

    // These properties mock the inverters in inventory
    private String name;
    private String model;
    private double capacityKva;
    private Integer systemVoltage;
    private String type = "Sine";
    //---------------------end---------------------
    private Double calculatedInverterCapacityKva;
    private Configuration configuration;

    public InverterSpecs(Inverter inverter, double recommendation, Configuration config){
        this.name= inverter.getName();
        this.model = inverter.getModel();
        this.capacityKva = inverter.getCapacity();
        this.systemVoltage = inverter.getSystemVoltage();
        this.type = inverter.getType();
        configuration = config;
        calculatedInverterCapacityKva = recommendation;
    }

}
