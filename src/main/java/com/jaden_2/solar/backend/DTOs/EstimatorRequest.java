package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimatorRequest {
    private Double energy; // estimated daily energy consumption
    private Double load; // estimated load of appliances
    private int daysOfAutonomy; // number of days system should last without recharge
    private Double psh; // peak sun hour in area energy per square meter per day
    private Integer systemVolt; // battery voltage
    private PanelRequest preferredPanel;//
    private int arrayStringLength; // length of series connection
    private double loadOnBattery = 1; // percentage load to be stored on battery default 100% load on battery
    private BatteryCategory batteryType;
}
