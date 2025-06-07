package com.jaden_2.solar.backend.entities;

import com.jaden_2.solar.backend.DTOs.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
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
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private User createdFor;
    private Date createdAt;
    private Date updateAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private BatterySpecs batteryBank;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private ArraySpecs solarArray;
    @OneToOne(mappedBy = "id" , cascade = CascadeType.ALL)
    private BreakerSpecs dcBreaker;
    private List<WireDetails> wireDetails;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private InverterSpecs inverter;
    @OneToOne(mappedBy = "id" , cascade = CascadeType.ALL)
    private ControllerSpecs chargeController;

    public SystemReport(BatterySpecs bank, ArraySpecs arraySpecs, BreakerSpecs dcBreaker, List<WireDetails> wireDetails, InverterSpecs inverterSpecs, ControllerSpecs controllerSpecs, User creator) {
        setBatteryBank(bank);
        setSolarArray(arraySpecs);
        setDcBreaker(dcBreaker);
        setWireDetails(wireDetails);
        setInverter(inverterSpecs);
        setChargeController(controllerSpecs);
        setCreatedFor(creator);
    }

    @PrePersist
    private void setUpdate(){
        updateAt = new Date(System.currentTimeMillis());
    }
}
