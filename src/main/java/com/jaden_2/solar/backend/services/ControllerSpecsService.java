package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.ControllerSpecs;
import com.jaden_2.solar.backend.entities.User;
import com.jaden_2.solar.backend.repositories.ControllerSpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ControllerSpecsService {
    private final ControllerSpecsRepo repo;

    public ControllerSpecs getSpec(Integer id){
        return  repo.findById(id).orElseThrow();
    }

    public ControllerSpecs getSpecByUser(Integer id, User creator){
        return  repo.findByIdAndCreator(id, creator).orElseThrow();
    }

    public void createSpec(ControllerSpecs specs){
        assert specs!=null: "Cannot create charge controller specs of null";
        repo.save(specs);
    }

    public ControllerSpecs updateSpec(ControllerSpecs updatedSpec, Integer id){
        assert updatedSpec!=null:"Cannot update specs with null";
        ControllerSpecs specs = repo.findById(id).orElseThrow();
        specs.setBrand(updatedSpec.getBrand());
        specs.setType(updatedSpec.getType());
        specs.setModel(updatedSpec.getModel());
        specs.setMaximumVolts(updatedSpec.getMaximumVolts());
        specs.setMinimumVolts(updatedSpec.getMinimumVolts());
        specs.setMaximumChargeCurrent(updatedSpec.getMaximumChargeCurrent());
        specs.setConfiguration(updatedSpec.getConfiguration());
        repo.save(specs);
        return specs;
    }
    public void deleteSpec(Integer id){
        repo.deleteById(id);
    }
    public ComparisonResult compareSpecs(ControllerSpecs modified, Integer id){
        ControllerSpecs old = repo.findById(id).orElseThrow();
        double calcCap = old.getMaximumChargeCurrent();
        double newCap = modified.getConfiguration().getTotal() * modified.getMaximumChargeCurrent();
        double oldCap = old.getMaximumChargeCurrent() * old.getConfiguration().getTotal();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New charge controller configuration is smaller than recommended capacity of "+
                calcCap: "New charge controller configuration is larger by "+diff+"%";
        String name = modified.getBrand() + " " + modified.getModel();
        return new ComparisonResult(name, diff, recommendation);
    }
}
