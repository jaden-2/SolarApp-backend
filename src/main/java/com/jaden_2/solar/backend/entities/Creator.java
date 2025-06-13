package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.entities.converters.RoleConverter;
import com.jaden_2.solar.backend.entities.enums.Roles;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(schema = "solar_inventory", name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Creator {
    @Id
    private String username;
    @NotBlank
    @Size(min = 8, message = "Password too short")
    private String password;

    @Convert(converter = RoleConverter.class)
    private Roles role;
    /**
     * Create a dummy creator object for persisting system reports*/
    public Creator(String username){
        setUsername(username);
    }

}
