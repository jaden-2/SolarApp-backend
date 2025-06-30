package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InverterSpecsRepo extends JpaRepository<InverterSpecs, Integer> {
    public List<InverterSpecs> findAllByCreator(Creator creator);

    Optional<InverterSpecs> findByInverterIdAndCreator(int id, Creator creator);
}
