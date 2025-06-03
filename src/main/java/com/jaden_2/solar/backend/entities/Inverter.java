package com.jaden_2.solar.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "solar_inventory", name = "inverters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inverter {
    @Id
    private Integer id;
    private String name;
    private String model;
    private Double capacity;
    private Integer systemVoltage;
    private String type;
}
