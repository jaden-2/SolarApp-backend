package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.ArraySpecs;
import com.jaden_2.solar.backend.entities.BreakerSpecs;
import com.jaden_2.solar.backend.entities.User;
import com.jaden_2.solar.backend.repositories.BreakerRepo;
import com.jaden_2.solar.backend.repositories.BreakerSpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BreakerSpecsService {
    private final BreakerSpecsRepo repo;

    public BreakerSpecs getSpec(Integer id){
        return repo.findById(id).orElseThrow();
    }
    public BreakerSpecs getSpecByUser(int id, User creator){
        return repo.findByIdAndCreator(id, creator).orElseThrow();
    }

    public void createSpec(BreakerSpecs spec){
        assert spec!=null: "Cannot create specification for breaker that is null";
        repo.save(spec);
    }
    public BreakerSpecs updateSpec(BreakerSpecs updatedSpec, Integer id){
        BreakerSpecs spec = repo.findById(id).orElseThrow();
        spec.setModel(updatedSpec.getModel());
        spec.setType(updatedSpec.getType());
        spec.setCurrent(updatedSpec.getCurrent());
        spec.setMaximumVoltage(updatedSpec.getMaximumVoltage());

        repo.save(spec);
        return spec;
    }
    public void deleteSpec(Integer id){
        repo.deleteById(id);
    }

    public ComparisonResult compareSpecs(BreakerSpecs modified, Integer id){
        BreakerSpecs old = repo.findById(id).orElseThrow();
        double calcCap = old.getCalculatedCapacity();
        double newCap = modified.getCurrent();
        double oldCap = old.getCurrent();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New breaker is smaller than recommended capacity of "+
                calcCap: "New breaker is larger by "+diff+"%";
        String name = modified.getModel() + " " + modified.getType();
        return new ComparisonResult(name, diff, recommendation);
    }
}
