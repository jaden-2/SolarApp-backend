package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InverterSpecsRepo extends JpaRepository<InverterSpecs, Integer> {
    public List<InverterSpecs> findAllByCreator(User creator);

    Optional<InverterSpecs> findByIdAndCreator(int id, User creator);
}
