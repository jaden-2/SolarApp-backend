package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.BatterySpecs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatterySpecsRepo extends JpaRepository<BatterySpecs, Integer> {
}
