package com.jaden_2.solar.backend.entities.enums;

import lombok.Getter;

@Getter
public enum PanelType {
    POLY(0.85),
    MONO(0.65);
    // mono-crystalline
    private final double efficiency;

    PanelType(double eff){
        this.efficiency = eff;
    }
}
