package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(schema = "solar_inventory", name = "users")
public class User {
    @Id
    private String username;
    private Roles role;
}
