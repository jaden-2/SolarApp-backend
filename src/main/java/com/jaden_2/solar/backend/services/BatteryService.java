package com.jaden_2.solar.backend.services;


import com.jaden_2.solar.backend.entities.BatterySpecs;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import com.jaden_2.solar.backend.entities.inventory.Battery;
import com.jaden_2.solar.backend.repositories.BatteryRepo;
import jakarta.persistence.EntityNotFoundException;
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
        List<Battery> batteries;
            batteries = repo.findByTypeAndVoltageLessThanEqualOrderByEnergyCapacityAsc(type, sysVolts);
            battery = filterBattery(batteries, capacity).orElseThrow();
            Integer parallel = (int) Math.ceil(capacity/ battery.getCurrentCapacity());
            Integer series = (int) Math.ceil(sysVolts/battery.getVoltage());

            return  new BatterySpecs(battery, capacity, new Configuration(series, parallel));
    }
    public Optional<Battery> filterBattery(List<Battery> batteries, double capacity){
        return batteries.stream().min(Comparator.comparingDouble(b->Math.abs( (capacity /b.getCurrentCapacity()) - 1)));
    }
    public List<Battery> getBatteries() {
        return repo.findAll();
    }
    public  Battery getBattery(Integer id) throws EntityNotFoundException{
        return repo.findById(id).orElseThrow(()->new EntityNotFoundException("Invalid batteryId"));
    }
}
