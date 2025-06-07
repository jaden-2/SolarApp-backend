package com.jaden_2.solar.backend.entities.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "solar_inventory", name = "breakers")
@NoArgsConstructor
@Data
public class Breaker {
    @Id
    @JsonIgnore
    private Integer id;
    private String model;
    private Integer current;
    private Integer max_voltage;
    private String type;
}
