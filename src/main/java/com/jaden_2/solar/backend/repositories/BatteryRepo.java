package com.jaden_2.solar.backend.repositories;

import com.jaden_2.solar.backend.entities.inventory.Battery;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatteryRepo extends JpaRepository<Battery, Integer> {
    /**
     * Fetches battery from db that is withing the range of system voltage and matches battery type. Ordering by current
     * capacity to get the largest battery for project
     * @param systemVoltage System voltage
     * @param type Type of battery requested
     * @return Optional Battery*/
    public Optional<Battery> findTopByTypeAndVoltageLessThanEqualOrderByCurrentCapacityDesc(BatteryCategory type, int systemVoltage);
    /**
     * Fetches battery from db that is withing the range of system voltage and matches battery type. Ordering by energy_wh storage
     * capacity to get the largest battery for project
     *
     * @param type          Type of battery requested
     * @param systemVoltage System voltage
     * @return Optional Battery
     */
    public List<Battery> findByTypeAndVoltageLessThanEqualOrderByEnergyCapacityAsc(BatteryCategory type, int systemVoltage);

    Battery findTopByTypeAndVoltageLessThanEqualAndCurrentCapacityLessThanEqualOrderByEnergyCapacityDesc(BatteryCategory type, int sysVolts, int capacity);
}
