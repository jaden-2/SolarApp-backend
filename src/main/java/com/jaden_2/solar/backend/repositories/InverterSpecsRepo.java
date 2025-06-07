package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.InverterSpecs;
import com.jaden_2.solar.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InverterSpecsRepo extends JpaRepository<InverterSpecs, Integer> {
    public List<InverterSpecs> findAllByCreator(User creator);
}
