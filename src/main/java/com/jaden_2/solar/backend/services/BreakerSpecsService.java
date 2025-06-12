package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.entities.BreakerSpecs;
import com.jaden_2.solar.backend.entities.Creator;
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
    public BreakerSpecs getSpecByUser(int id, Creator creator){
        return repo.findByBreakerIdAndCreator(id, creator).orElseThrow();
    }

    public void createSpec(BreakerSpecs spec){
        assert spec!=null: "Cannot create specification for breaker that is null";
        repo.save(spec);
    }
    public BreakerSpecs updateSpec(BreakerSpecs updatedSpec, BreakerSpecs originalSpec){
        originalSpec.setModel(updatedSpec.getModel());
        originalSpec.setType(updatedSpec.getType());
        originalSpec.setCurrent(updatedSpec.getCurrent());
        originalSpec.setMaximumVoltage(updatedSpec.getMaximumVoltage());

        repo.save(originalSpec);
        return originalSpec;
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
