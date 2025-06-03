package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.Breaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreakerRepo extends JpaRepository<Breaker, Integer> {
    Optional<Breaker> findTopByTypeAndCurrentGreaterThanEqualOrderByCurrentAsc(String type, Double current);
}
