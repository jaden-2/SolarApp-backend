package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Panel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PanelRequest {
    private String brand;
    private Integer power;

    public PanelRequest(Panel panel){
        brand = panel.getBrand();
        power = panel.getPower();
    }
}
