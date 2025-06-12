package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.DTOs.ReportDTO;
import com.jaden_2.solar.backend.DTOs.sheets.WireDetails;
import com.jaden_2.solar.backend.entities.converters.WireDetailsTypeConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A full system report */
@Data
@NoArgsConstructor
@Table(schema = "solar_inventory", name = "systemReport")
@Entity
public class SystemReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer reportId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", nullable = false)
    private Creator creator;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "batteryId")
    private BatterySpecs batteryBank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrayId")
    private ArraySpecs solarArray;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "breakerId")
    private BreakerSpecs dcBreaker;

    @Convert(converter = WireDetailsTypeConverter.class)
    private List<WireDetails> wireDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inverterId")
    private InverterSpecs inverter;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "controllerId")
    private ControllerSpecs chargeController;

    public SystemReport(BatterySpecs bank, ArraySpecs arraySpecs, BreakerSpecs dcBreaker, List<WireDetails> wireDetails, InverterSpecs inverterSpecs, ControllerSpecs controllerSpecs, Creator creator) {
        setBatteryBank(bank);
        setSolarArray(arraySpecs);
        setDcBreaker(dcBreaker);
        setWireDetails(wireDetails);
        setInverter(inverterSpecs);
        setChargeController(controllerSpecs);
        setCreator(creator);
    }

    @PrePersist
    private void setCreatedAt(){
        createdAt = LocalDateTime.now();
    }

    public SystemReport updateReport(ReportDTO report){
        setCreatedAt(report.getCreatedAt());
        setUpdateAt(report.getUpdateAt());
        setBatteryBank(report.getBatteryBank());
        setSolarArray(report.getSolarArray());
        setDcBreaker(report.getDcBreaker());
        setInverter(report.getInverter());
        setChargeController(report.getChargeController());
        setUpdateAt(LocalDateTime.now());
        return this;
    }
}
