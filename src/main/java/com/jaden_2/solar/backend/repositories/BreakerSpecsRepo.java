package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.BreakerSpecs;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreakerSpecsRepo extends JpaRepository<BreakerSpecs, Integer> {
    Optional<BreakerSpecs> findByBreakerIdAndCreator(Integer id, Creator creator);
}
