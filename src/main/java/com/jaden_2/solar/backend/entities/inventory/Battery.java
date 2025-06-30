package com.jaden_2.solar.backend.entities.inventory;

import com.jaden_2.solar.backend.entities.converters.BatteryTypeConverter;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "solar_inventory",name = "batteries")
@Data
@NoArgsConstructor
public class Battery {
    @Id
    private Integer id;
    private String brand;
    private Integer currentCapacity;
    private double energyCapacity;
    private double voltage;

    @Convert(converter = BatteryTypeConverter.class)
    private BatteryCategory type;
    private Integer chargeCycle;
    private String model;

}
