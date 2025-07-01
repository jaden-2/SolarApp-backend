package com.jaden_2.solar.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaden_2.solar.backend.entities.inventory.ChargeController;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A specification sheet for the charge controller
 * @author Sylvanus Jedidiah
 * @version 1.0
 * @since 2025*/
@Data
@NoArgsConstructor
@Entity
public class ControllerSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer controllerId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username", name = "creator", nullable = false)
    @JsonIgnore
    private Creator creator;

    private String brand;
    private String model;
    private String type;
    private int maximumChargeCurrent;
    private int minimumVolts;
    private int maximumVolts;

    private double calculatedCapacity;
    private Configuration configuration;

    public ControllerSpecs(ChargeController controller, double cap,Configuration config){
        brand = controller.getBrand();
        model = controller.getModel();
        type = controller.getType();
        maximumChargeCurrent = controller.getMaxChargeCurrent();
        minimumVolts = controller.getMinVoltage();
        maximumVolts = controller.getMaxVoltage();
        configuration = config;
        calculatedCapacity = cap;
    }
}
