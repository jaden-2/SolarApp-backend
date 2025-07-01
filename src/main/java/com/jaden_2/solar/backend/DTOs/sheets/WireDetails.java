package com.jaden_2.solar.backend.DTOs.sheets;


import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A record that contains details of the Dc cable*/
@Data
@NoArgsConstructor
public class WireDetails{
        private String type;
        private double maximumAllowedWireLength_m;
        private double powerLoss;
        private WireSpec wireSpecification;
        private String lengthType = "Round-Trip";
        private String recommendation = "Minimize cost by keeping cable length below 50m";

        public WireDetails(String type, double maxWireLenght, double powerLoss, WireSpec wireSpec){
                this.type = type;
                maximumAllowedWireLength_m = maxWireLenght;
                this.powerLoss = powerLoss;
                wireSpecification = wireSpec;
        }
}

