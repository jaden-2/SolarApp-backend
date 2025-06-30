package com.jaden_2.solar.backend.DTOs.sheets;

import com.jaden_2.solar.backend.entities.enums.PanelType;
import jakarta.persistence.Embeddable;

@Embeddable
public record TechSheet(int power_w, double Vmax, PanelType type, double Imp, double Isc, double Voc){ }
