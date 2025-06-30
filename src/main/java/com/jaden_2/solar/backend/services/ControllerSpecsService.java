package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.ControllerSpecs;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.inventory.ChargeController;
import com.jaden_2.solar.backend.repositories.ControllerSpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ControllerSpecsService {
    private final ControllerSpecsRepo repo;
    private final ControllerService service;
    public ControllerSpecs getSpec(Integer id){
        return  repo.findById(id).orElseThrow();
    }

    public ControllerSpecs getSpecByUser(Integer id, Creator creator){
        return  repo.findByControllerIdAndCreator(id, creator).orElseThrow();
    }

    public ControllerSpecs createSpec(Integer controllerId, Double recommendedCap){
        ChargeController controller = service.getControllerById(controllerId);
        int parallel = (int) Math.ceil(recommendedCap/controller.getMaxChargeCurrent());
        return new ControllerSpecs(controller, recommendedCap, new Configuration(1, parallel));
    }

    public void saveSpec(ControllerSpecs specs){
        assert specs!=null: "Cannot create charge controller specs of null";
        repo.save(specs);
    }

    public ControllerSpecs updateSpec(ControllerSpecs updatedSpec, ControllerSpecs specs){
        assert updatedSpec!=null && specs!=null:"Cannot update specs with null";
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
