package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.inventory.Breaker;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(schema = "solar_inventory")
@Entity
public class BreakerSpecs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToMany(mappedBy = "username")
    private User creatorFor;

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
