package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.converters.RoleConverter;
import com.jaden_2.solar.backend.entities.enums.Roles;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(schema = "solar_inventory", name = "users")
public class Creator {
    @Id
    private String username;
    @Convert(converter = RoleConverter.class)
    private Roles role;

    @NotBlank
    @Size(min = 8, message = "Password too short")
    private String password;

}
