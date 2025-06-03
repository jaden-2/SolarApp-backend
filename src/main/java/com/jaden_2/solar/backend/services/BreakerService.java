package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.BreakerSpecs;
import com.jaden_2.solar.backend.entities.Breaker;
import com.jaden_2.solar.backend.repositories.BreakerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
