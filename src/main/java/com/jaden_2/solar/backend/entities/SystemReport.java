package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.DTOs.ReportDTO;
import com.jaden_2.solar.backend.DTOs.sheets.WireDetails;
import com.jaden_2.solar.backend.entities.converters.WireDetailsTypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    private String title;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "energy_wh", referencedColumnName = "energy_wh", nullable = false)
    @JoinColumn(name = "load_w", referencedColumnName = "load_w", nullable = false)
    @JoinColumn(name = "psh", referencedColumnName = "psh", nullable = false)
    @JoinColumn(name = "sysVolts", referencedColumnName = "systemVolts", nullable = false)
    @JoinColumn(name = "panelBrand", referencedColumnName = "brand", nullable = false)
    @JoinColumn(name = "panelPower", referencedColumnName = "power", nullable = false)
    @JoinColumn(name = "arraySeries", referencedColumnName = "arraySeriesLength", nullable = false)
    @JoinColumn(name = "batteryType", referencedColumnName = "batteryType", nullable = false)
    @JoinColumn(name = "daysOfBackup", referencedColumnName = "daysOfBackup", nullable = false)
    private Request request;
    @ManyToOne
    @JoinColumn(referencedColumnName = "username", name = "creator", nullable = false)
    private Creator creator;
    private ZonedDateTime createdAt;
    private ZonedDateTime updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "batteryId", name = "batteryBank")
    private BatterySpecs batteryBank;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "arrayId", name = "solarArray")
    private ArraySpecs solarArray;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "breakerId", name = "dcBreaker")
    private BreakerSpecs dcBreaker;

    @Convert(converter = WireDetailsTypeConverter.class)
    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<WireDetails> wireDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "inverterId", name = "inverter")
    private InverterSpecs inverter;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "controllerId", name = "controller")
    private ControllerSpecs chargeController;

    public SystemReport(BatterySpecs bank, ArraySpecs arraySpecs, BreakerSpecs dcBreaker, List<WireDetails> wireDetails, InverterSpecs inverterSpecs, ControllerSpecs controllerSpecs, Creator creator, Request request) {
        setBatteryBank(bank);
        setSolarArray(arraySpecs);
        setDcBreaker(dcBreaker);
        setWireDetails(wireDetails);
        setInverter(inverterSpecs);
        setChargeController(controllerSpecs);
        setCreator(creator);
        setRequest(request);
    }

    @PrePersist
    private void setCreatedAt(){
        createdAt = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
    }

    public SystemReport updateReport(ReportDTO report){
        setCreatedAt(report.getCreatedAt());
        setUpdateAt(report.getUpdateAt());
        setBatteryBank(report.getBatteryBank());
        setSolarArray(report.getSolarArray());
        setDcBreaker(report.getDcBreaker());
        setInverter(report.getInverter());
        setChargeController(report.getChargeController());
        setUpdateAt(ZonedDateTime.now(ZoneId.systemDefault()));
        return this;
    }
}
