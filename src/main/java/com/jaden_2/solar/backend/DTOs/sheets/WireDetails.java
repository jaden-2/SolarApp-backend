package com.jaden_2.solar.backend.DTOs.sheets;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A record that contains details of the Dc cable*/
@Data
@RequiredArgsConstructor
public class WireDetails{
        private final String type;
        private final double maximumAllowedWireLength_m;
        private final double powerLoss;
        private final WireSpec wireSpecification;
        private String lengthType = "Round-Trip";
        private String recommendation = "Minimize cost by keeping cable length below 50m";
}

