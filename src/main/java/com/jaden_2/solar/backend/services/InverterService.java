package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.inventory.Inverter;
import com.jaden_2.solar.backend.repositories.InverterRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InverterService {
    private final InverterRepo repo;

    /**
     * Returns specification of inverter
     * @param inverterCapacity Estimated capacity of inverter in VA
     * @param sysVolts Voltage of system*/
    public InverterSpecs evaluateInverter(double inverterCapacity, int sysVolts){
        Inverter inverter = getClosestInverter(inverterCapacity, sysVolts).orElseThrow();
        int parallel = (int) Math.ceil(inverterCapacity/inverter.getCapacity());
        return new InverterSpecs(inverter, inverterCapacity, new Configuration(1, parallel));
    }

    public InverterSpecs evaluateInverter_deprecated(double inverterCapacity, int sysVolts){
        Inverter inverter;
        try{
            inverter = repo.findTopBySystemVoltageAndCapacityGreaterThanEqualOrderByCapacityAsc(sysVolts, inverterCapacity)
                    .orElseThrow();
            return new InverterSpecs(inverter, inverterCapacity, new Configuration(1, 1));
        } catch (RuntimeException e) {
            inverter = repo.findTopBySystemVoltageAndCapacityLessThanOrderByCapacityAsc(sysVolts, inverterCapacity);
            int parallel = (int) Math.ceil(inverterCapacity/inverter.getCapacity());
            return new InverterSpecs(inverter, inverterCapacity, new Configuration(1, parallel));
        }
    }
    public Inverter getInverterById(Integer inverterId) throws EntityNotFoundException {
        return  repo.findById(inverterId).orElseThrow(()-> new EntityNotFoundException("Invalid inverterId"));
    }
    public List<Inverter> getInverters(){
        return repo.findAll();
    }

    private Optional<Inverter> getClosestInverter(double capacity, int sysVolts){
        List<Inverter> inverters = repo.findAllBySystemVoltage(sysVolts);
        return inverters.stream().min(Comparator.comparingDouble(inv-> Math.abs((capacity/inv.getCapacity()) - 1)));
    }
}
