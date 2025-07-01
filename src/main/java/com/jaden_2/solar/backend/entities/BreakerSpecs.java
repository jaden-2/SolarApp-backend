package com.jaden_2.solar.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaden_2.solar.backend.entities.inventory.Breaker;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class BreakerSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer breakerId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username", name = "creator", nullable = false)
    @JsonIgnore
    private Creator creator;

    private String model;
    private Integer current;
    private Integer maximumVoltage;
    private String type;
    private Double calculatedCapacity;

    public BreakerSpecs(Breaker breaker, double capacity){
        model = breaker.getModel();
        current = breaker.getCurrent();
        maximumVoltage = breaker.getMax_voltage();
        type = breaker.getType();
        calculatedCapacity = capacity;
    }
    public BreakerSpecs(double capacity){
        calculatedCapacity = capacity;
    }
}
