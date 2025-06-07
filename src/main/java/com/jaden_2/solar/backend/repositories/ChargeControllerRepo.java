package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.inventory.ChargeController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargeControllerRepo extends JpaRepository<ChargeController, Integer> {
    public Optional<ChargeController> findTopByMaxChargeCurrentGreaterThanEqualOrderByMaxChargeCurrentAsc(double capacity);

    ChargeController findTopByMaxChargeCurrentLessThanEqualOrderByMaxChargeCurrentAsc(double capacity);
}
