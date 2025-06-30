package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.inventory.Inverter;
import com.jaden_2.solar.backend.repositories.InverterSpecsRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InverterSpecsService {
    private final InverterSpecsRepo repo;
    private final InverterService service;

    public List<InverterSpecs> getSpecByUser(Creator creator){
        return repo.findAllByCreator(creator);
    }
    public InverterSpecs getSpecByUser(int id, Creator creator){
        return repo.findByInverterIdAndCreator(id, creator).orElseThrow();
    }

    public InverterSpecs getSpec(Integer id){
        return repo.findById(id).orElseThrow();
    }

    public InverterSpecs createSpec(Integer id, Double recommendCap, int sysVolts){
        Inverter inverter = service.getInverterById(id);
        int parallel = (int) Math.ceil(recommendCap/inverter.getCapacity());
        int series = (sysVolts/ inverter.getSystemVoltage());
        return new InverterSpecs(inverter, recommendCap, new Configuration(1, parallel));
    }
    public void saveSpec(InverterSpecs specs){
        assert specs!=null: "Cannot create a new inverter that's null";
        repo.save(specs);
    }

    public InverterSpecs updateSpec(InverterSpecs updatedSpecs, InverterSpecs originalSpecs){
        originalSpecs.setModel(updatedSpecs.getModel());
        originalSpecs.setType(updatedSpecs.getType());
        originalSpecs.setCapacityKva(updatedSpecs.getCapacityKva());
        originalSpecs.setConfiguration(updatedSpecs.getConfiguration());
        originalSpecs.setSystemVoltage(updatedSpecs.getSystemVoltage());

        repo.save(originalSpecs);
        return  originalSpecs;
    }
    public void deleteSpec(Integer id){
        repo.delete(repo.findById(id).orElseThrow());
    }
    /**
     * Compares the capacity of Inverters, can be extended to factor properties
     * @param newInverterSpecs New inverter to contrast with system recommendation
     * @param oldInverterId System recommended inverter specification*/
    public ComparisonResult compareSpecs(InverterSpecs newInverterSpecs, Integer oldInverterId){
        InverterSpecs oldInverter = repo.findById(oldInverterId).orElseThrow();
        double calcCap = oldInverter.getCalculatedInverterCapacityKva();
        double newCap = newInverterSpecs.getConfiguration().getTotal() * newInverterSpecs.getCapacityKva();
        double oldCap = oldInverter.getCapacityKva() * oldInverter.getConfiguration().getTotal();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New inverter setup is smaller than recommended capacity of "+
                calcCap: "New inverter setup is larger by "+diff+"%";
        String name = newInverterSpecs.getName() + " " + newInverterSpecs.getModel();
        return new ComparisonResult(name, diff, recommendation);
    }
}