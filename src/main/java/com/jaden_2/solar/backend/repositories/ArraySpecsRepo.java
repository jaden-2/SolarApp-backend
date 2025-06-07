package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.ArraySpecs;
import com.jaden_2.solar.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArraySpecsRepo extends JpaRepository<ArraySpecs, Integer> {
    Optional<ArraySpecs> findByIdAndCreator(Integer id, User creator);
}
