package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.inventory.Battery;
import lombok.Data;

@Data
public class BatteryDTO {
    private Integer Id;
    private Configuration config;
}
