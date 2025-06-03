package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Breaker;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BreakerSpecs {
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
