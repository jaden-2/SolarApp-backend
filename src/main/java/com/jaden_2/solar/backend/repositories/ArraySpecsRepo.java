package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.ArraySpecs;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArraySpecsRepo extends JpaRepository<ArraySpecs, Integer> {
    Optional<ArraySpecs> findByArrayIdAndCreator(Integer id, Creator creator);
}
