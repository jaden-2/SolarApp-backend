package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.ArraySpecs;
import com.jaden_2.solar.backend.entities.User;
import com.jaden_2.solar.backend.repositories.ArraySpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PanelSpecsService {
    private final ArraySpecsRepo repo;

    public ArraySpecs getSpec(Integer id){
        return repo.findById(id).orElseThrow();
    }
    public ArraySpecs getSpecByUser(Integer id, User creator){
        return repo.findByIdAndCreator(id, creator).orElseThrow();
    }


    public void createSpec(ArraySpecs spec){
        repo.save(spec);
    }

    public ArraySpecs updateSpec(ArraySpecs updatedArraySpec, Integer id){
        ArraySpecs spec = repo.findById(id).orElseThrow();
        spec.setBrand(updatedArraySpec.getBrand());
        spec.setModel(updatedArraySpec.getModel());
        spec.setElectricalProperties(updatedArraySpec.getElectricalProperties());
        spec.setMechanicalProperties(updatedArraySpec.getMechanicalProperties());
        spec.setConfiguration(updatedArraySpec.getConfiguration());
        spec.setWireSpecification(updatedArraySpec.getWireSpecification());

        repo.save(spec);
        return spec;
    }

    public void deleteSpec(int id){
        repo.deleteById(id);
    }

    public ComparisonResult compareSpecs(ArraySpecs modified, Integer id){
        ArraySpecs old = repo.findById(id).orElseThrow();
        double calcCap = old.getCalculatePanelCapacity();
        double newCap = modified.getConfiguration().getTotal() * modified.getElectricalProperties().power_w();
        double oldCap = old.getElectricalProperties().power_w() * old.getConfiguration().getTotal();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New solar array configuration is smaller than recommended capacity of "+
                calcCap: "New solar array configuration is larger by "+diff+"%";
        String name = modified.getBrand() + " " + modified.getModel();
        return new ComparisonResult(name, diff, recommendation);
    }
}
