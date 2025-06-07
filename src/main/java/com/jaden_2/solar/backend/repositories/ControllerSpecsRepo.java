package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.ControllerSpecs;
import com.jaden_2.solar.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ControllerSpecsRepo extends JpaRepository<ControllerSpecs, Integer> {
    Optional<ControllerSpecs> findByIdAndCreator(Integer id, User creator);
}
