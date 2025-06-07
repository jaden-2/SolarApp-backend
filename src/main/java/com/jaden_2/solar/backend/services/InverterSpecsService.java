package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.User;
import com.jaden_2.solar.backend.repositories.InverterSpecsRepo;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InverterSpecsService {
    private final InverterSpecsRepo repo;

    public List<InverterSpecs> getInverterSpecsByUser(User creator){
        return repo.findAllByCreator(creator);
    }

    public InverterSpecs getInverterSpecsById(Integer id){
        return repo.findById(id).orElseThrow();
    }

    public void createInverterSpecs(InverterSpecs specs){
        assert specs!=null: "Cannot create a new inverter that's null";
        repo.save(specs);
    }

    public void updateInverterSpecs(InverterSpecs updatedSpecs, Integer id){
        InverterSpecs specs = repo.findById(id).orElseThrow();
        specs.setModel(updatedSpecs.getModel());
        specs.setType(updatedSpecs.getType());
        specs.setCapacityKva(updatedSpecs.getCapacityKva());
        specs.setConfiguration(updatedSpecs.getConfiguration());
        specs.setSystemVoltage(updatedSpecs.getSystemVoltage());

        repo.save(specs);
    }
    public void deleteInverterSpecs(Integer id){
        repo.delete(repo.findById(id).orElseThrow());
    }
    /**
     * Compares the capacity of Inverters, can be extended to factor properties
     * @param newInverterSpecs New inverter to contrast with system recommendation
     * @param oldInverterId System recommended inverter specification*/
    public ComparisonResult compareInverter(InverterSpecs newInverterSpecs, Integer oldInverterId){
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