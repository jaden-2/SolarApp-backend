package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.inventory.Panel;
import lombok.Data;

@Data
public class ArrayDTO {
    private PanelRequest panel;
    private Configuration config;
}
