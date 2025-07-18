package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.BatterySpecs;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.inventory.Battery;
import com.jaden_2.solar.backend.repositories.BatterySpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BatterySpecsService {
    private final BatterySpecsRepo repo;
    private final BatteryService service;
    public BatterySpecs getSpec(int id){
        return repo.findById(id).orElseThrow();
    }
    public BatterySpecs getSpecByUser(int id, Creator creator){
        return repo.findByBatteryIdAndCreator(id, creator).orElseThrow();
    }

    public BatterySpecs createSpec(int changedBattery, double calcCapacity, int sysVolts){
        Battery battery = service.getBattery(changedBattery);
        int series = (int) Math.ceil(sysVolts/ battery.getVoltage());
        int parallel = (int) Math.ceil(calcCapacity/ battery.getCurrentCapacity());
        return new BatterySpecs(battery, calcCapacity, new Configuration(series, parallel));
    }

    public void saveSpec(BatterySpecs spec){
        repo.save(spec);
    }
    public void deleteSpec(int id){
        repo.deleteById(id);
    }
    public BatterySpecs updateSpec(BatterySpecs updatedSpec, BatterySpecs spec){
        spec.setBatteryType(updatedSpec.getBatteryType());
        spec.setConfiguration(updatedSpec.getConfiguration());
        spec.setBrand(updatedSpec.getBrand());
        spec.setBatteryVoltage(updatedSpec.getBatteryVoltage());
        spec.setBatteryCurrentCapacityAh(updatedSpec.getBatteryCurrentCapacityAh());
        spec.setBatteryEnergyCapacityAh(updatedSpec.getBatteryEnergyCapacityAh());
        repo.save(spec);

        return spec;
    }
    public ComparisonResult compareSpecs(BatterySpecs modified, Integer id){
        BatterySpecs old = repo.findById(id).orElseThrow();
        double calcCap = old.getRequiredBankCapacityAh();
        double newCap = modified.getConfiguration().getTotal() * modified.getBatteryCurrentCapacityAh();
        double oldCap = old.getBatteryCurrentCapacityAh() * old.getConfiguration().getTotal();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New solar array configuration is smaller than recommended capacity of "+
                calcCap: "New solar array configuration is larger by "+diff+"%";
        String name = modified.getBrand();
        return new ComparisonResult(name, diff, recommendation);
    }
}
