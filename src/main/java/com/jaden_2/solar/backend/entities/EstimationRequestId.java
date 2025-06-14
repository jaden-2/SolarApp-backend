package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.DTOs.EstimatorRequest;
import com.jaden_2.solar.backend.DTOs.PanelRequest;
import com.jaden_2.solar.backend.entities.converters.BatteryTypeConverter;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class EstimationRequestId implements Serializable {

    private Double energy_wh;
    private Double load_w;
    private Double psh;
    private Integer systemVolts;
    @Embedded
    private PanelRequest selectedPanel;
    private Integer arraySeriesLength;
    @Convert(converter = BatteryTypeConverter.class)
    private BatteryCategory batteryType;

    public EstimationRequestId(EstimatorRequest request){
        setEnergy_wh(request.getEnergy_wh());
        setLoad_w(request.getLoad_w());
        setPsh(request.getPsh());
        setSystemVolts(request.getSystemVolt());
        setSelectedPanel(request.getPreferredPanel());
        setArraySeriesLength(request.getArraySeriesLength());
        setBatteryType(request.getBatteryType());
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EstimationRequestId that)) return false;
        return Objects.equals(energy_wh, that.energy_wh) && Objects.equals(load_w, that.load_w) && Objects.equals(psh, that.psh) && Objects.equals(systemVolts, that.systemVolts) && Objects.equals(selectedPanel, that.selectedPanel) && Objects.equals(arraySeriesLength, that.arraySeriesLength) && batteryType == that.batteryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy_wh, load_w, psh, systemVolts, selectedPanel, arraySeriesLength, batteryType);
    }
}
