package com.jaden_2.solar.backend.DTOs;

import java.util.List;

/**
 * A full system report */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "solar_inventory", name = "systemReport")
@Entity
public class SystemReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
    private BatterySpecs batteryBank;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private ArraySpecs solarArray;
    @OneToOne(mappedBy = "id" , cascade = CascadeType.ALL)
    private BreakerSpecs DcBreaker;
    private List<WireDetails> wireDetails;
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)
    private InverterSpecs inverter;
    @OneToOne(mappedBy = "id" , cascade = CascadeType.ALL)
    private ControllerSpecs chargeController;
}
