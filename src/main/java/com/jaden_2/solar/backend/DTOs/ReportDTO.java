package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.DTOs.sheets.WireDetails;
import com.jaden_2.solar.backend.entities.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
@Data
public class ReportDTO {
    @Positive
    @NotNull
    private Integer reportId;

    @Size(min = 5, max = 400)
    private String title;
    @NotNull
    private BatterySpecs batteryBank;

    @NotNull
    private ArraySpecs solarArray;

    @NotNull
    private BreakerSpecs dcBreaker;
    @NotNull
    private List<WireDetails> wireDetails;

    @NotNull
    private InverterSpecs inverter;

    @NotNull
    private ControllerSpecs chargeController;

    @PastOrPresent
    private ZonedDateTime createdAt;
    private ZonedDateTime updateAt;

    public ReportDTO(SystemReport report){
        setReportId(report.getReportId());
        setBatteryBank(report.getBatteryBank());
        setSolarArray(report.getSolarArray());
        setDcBreaker(report.getDcBreaker());
        setWireDetails(report.getWireDetails());
        setInverter(report.getInverter());
        setChargeController(report.getChargeController());
        setCreatedAt(report.getCreatedAt());
        setUpdateAt(report.getUpdateAt());
        setTitle(report.getRequest().getRequestId().getTitle());
    }
}
