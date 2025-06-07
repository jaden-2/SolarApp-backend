package com.jaden_2.solar.backend.entities.inventory;

import com.jaden_2.solar.backend.entities.converters.PanelTypeConverter;
import com.jaden_2.solar.backend.entities.enums.PanelType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "solar_inventory", name = "panels")
@Data
@NoArgsConstructor
public class Panel{
    @Id
    private Integer id;
    private String brand;
    private String model;
    private Integer power;
    private Double vmp;
    private Double imp;
    private Double voc;
    private Double isc;
    @Column(name = "lenght_m")
    private Double lenght;
    @Column(name = "width_m")
    private Double width;
    @Column(name = "type") // A little mix-up in db
    private String design;
    @Column(name = "design") // A little mix-up in db
    @Convert(converter = PanelTypeConverter.class)
    private PanelType type;
}
