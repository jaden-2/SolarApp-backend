package com.jaden_2.solar.backend.entities.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum DcCable {
    COPPER(1.68e-8),
    ALUMINUM(2.65e-8);
    private final double resistivity;

    DcCable(double resistivity){
        this.resistivity = resistivity;
    }
}
