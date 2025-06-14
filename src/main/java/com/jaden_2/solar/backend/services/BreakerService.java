package com.jaden_2.solar.backend.services;


import com.jaden_2.solar.backend.entities.BreakerSpecs;
import com.jaden_2.solar.backend.entities.inventory.Breaker;
import com.jaden_2.solar.backend.repositories.BreakerRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BreakerService {
    private final BreakerRepo repo;

    public BreakerSpecs evaluateBreaker(double capacity, String type){
        capacity = (int) Math.ceil(capacity);
        try{
            Breaker breaker = repo.findTopByTypeAndCurrentGreaterThanEqualOrderByCurrentAsc(type, capacity).orElseThrow();
            return new BreakerSpecs(breaker, capacity);
        } catch (RuntimeException e) {
            return new BreakerSpecs(capacity);
        }
    }
    public List<Breaker> getBreakers(){
        return repo.findAll();
    }
    public Breaker getBreaker(Integer breakerId) throws EntityNotFoundException{
        return  repo.findById(breakerId).orElseThrow(()-> new EntityNotFoundException("Invalid breaker ID"));
    }
}
