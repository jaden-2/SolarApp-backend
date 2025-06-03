package com.jaden_2.solar.backend.DTOs;

import java.util.List;

/**
 * A full system report */
public record SystemReport(BatterySpecs batteryBank, ArraySpecs solarArray, BreakerSpecs DcBreaker, List<WireDetails> wireDetails, InverterSpecs inverter, ControllerSpecs chargeController) {
}
