package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.inventory.ChargeController;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A specification sheet for the charge controller
 * @author Sylvanus Jedidiah
 * @version 1.0
 * @since 2025*/
@Data
@NoArgsConstructor
public class ControllerSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToMany(mappedBy = "username")
    private User creatorFor;

    private String brand;
    private String model;
    private String type;
    private int maximumChargeCurrent;
    private int minimumVolts;
    private int maximumVolts;
    private double calculatedCapacity;
    private Configuration<Integer, Integer> configuration;

    public ControllerSpecs(ChargeController controller, double cap,Configuration<Integer, Integer> config){
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
