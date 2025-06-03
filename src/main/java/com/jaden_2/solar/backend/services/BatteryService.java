package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.BatterySpecs;
import com.jaden_2.solar.backend.entities.Battery;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import com.jaden_2.solar.backend.exceptions.BatteryTypeAndCapacityNotFoundException;
import com.jaden_2.solar.backend.repositories.BatteryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BatteryService {
    private final BatteryRepo repo;

    public BatterySpecs evaluateBattery(double capacity, BatteryCategory type, int sysVolts){
        Battery battery;
        try{
            battery = repo.findTopByTypeAndVoltageGreaterThanEqualAndCurrentCapacityGreaterThanEqualOrderByEnergyCapacityAsc(type, sysVolts, (int)capacity).orElseThrow(()->new BatteryTypeAndCapacityNotFoundException("Battery type not found"));
            Integer parallel = (int) Math.ceil(capacity/ battery.getCurrentCapacity());
            Integer series = (int) (sysVolts/battery.getVoltage());
            return  new BatterySpecs(battery, capacity, new Configuration<>(series, parallel));
        }catch(BatteryTypeAndCapacityNotFoundException e){
            battery = repo.findTopByTypeAndVoltageLessThanEqualAndCurrentCapacityLessThanEqualOrderByEnergyCapacityDesc(type, sysVolts, (int)capacity);
            Integer parallel = (int) Math.ceil(capacity/ battery.getCurrentCapacity());
            Integer series = (int) (sysVolts/battery.getVoltage());

            return  new BatterySpecs(battery, capacity, new Configuration<>(series, parallel));
        }

    }
    public Optional<Battery> filterBattery(List<Battery> batteries, double capacity){
        return batteries.stream().min(Comparator.comparingDouble(b-> capacity /b.getCurrentCapacity()));
    }
    public List<Battery> getBatteries() {
        return repo.findAll();
    }
}
