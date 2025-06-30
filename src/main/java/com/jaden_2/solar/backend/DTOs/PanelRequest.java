package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.inventory.Panel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PanelRequest {
    private String brand;
    private Integer power;
}
