package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimatorRequest {
    @Positive
    private Double energy_wh; // estimated daily energy_wh consumption
    @Positive
    private Double load_w; // estimated load_w of appliances
    @Positive
    private int daysOfBackup; // number of days system should last without recharge
    @Positive
    private Double psh; // peak sun hour in area energy_wh per square meter per day
    @Positive
    private Integer systemVolt; // battery voltage
    @NotNull
    private PanelRequest preferredPanel;//
    @Positive
    @Min(1)
    private int arraySeriesLength; // length of series connection
    @Positive
    private double loadOnBattery = 1; // percentage load_w to be stored on battery default 100% load_w on battery
    @NotNull
    private BatteryCategory batteryType;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EstimatorRequest that)) return false;
        return daysOfBackup == that.daysOfBackup && arraySeriesLength == that.arraySeriesLength && Double.compare(loadOnBattery, that.loadOnBattery) == 0 && Objects.equals(energy_wh, that.energy_wh) && Objects.equals(load_w, that.load_w) && Objects.equals(psh, that.psh) && Objects.equals(systemVolt, that.systemVolt) && Objects.equals(preferredPanel, that.preferredPanel) && batteryType == that.batteryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy_wh, load_w, daysOfBackup, psh, systemVolt, preferredPanel, arraySeriesLength, loadOnBattery, batteryType);
    }
}
