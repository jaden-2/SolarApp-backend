package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.BatterySpecs;
import com.jaden_2.solar.backend.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatterySpecsRepo extends JpaRepository<BatterySpecs, Integer> {
    Optional<BatterySpecs> findByBatteryIdAndCreator(Integer id, Creator creator);
}
