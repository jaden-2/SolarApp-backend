package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.inventory.Panel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelRepo extends JpaRepository<Panel, Integer> {
    Panel findByBrandAndPower(String brand, Integer power);
}
