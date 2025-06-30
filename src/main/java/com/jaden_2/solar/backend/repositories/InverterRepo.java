package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.inventory.Inverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InverterRepo extends JpaRepository<Inverter, Integer> {
    Optional<Inverter> findTopBySystemVoltageAndCapacityGreaterThanEqualOrderByCapacityAsc(Integer sysVolts, Double capacity);

    Inverter findTopBySystemVoltageAndCapacityLessThanOrderByCapacityAsc(int sysVolts, double inverterCapacity);
    List<Inverter> findAllBySystemVoltage(int sysVolts);
}
