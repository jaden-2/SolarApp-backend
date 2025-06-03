package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.InverterSpecs;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.Inverter;
import com.jaden_2.solar.backend.repositories.InverterRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InverterService {
    private final InverterRepo repo;

    /**
     * Returns specification of inverter
     * @param inverterCapacity Estimated capacity of inverter in VA
     * @param sysVolts Voltage of system*/
    public InverterSpecs evaluateInverter(double inverterCapacity, int sysVolts){

        Inverter inverter;
        try{
            inverter = repo.findTopBySystemVoltageAndCapacityGreaterThanEqualOrderByCapacityAsc(sysVolts, inverterCapacity)
                    .orElseThrow();
            return new InverterSpecs(inverter, inverterCapacity, new Configuration<>(1, 1));
        } catch (RuntimeException e) {
            inverter = repo.findTopBySystemVoltageAndCapacityLessThanOrderByCapacityAsc(sysVolts, inverterCapacity);
            int parallel = (int) Math.ceil(inverterCapacity/inverter.getCapacity());
            return new InverterSpecs(inverter, inverterCapacity, new Configuration<>(1, parallel));
        }
    }

}
