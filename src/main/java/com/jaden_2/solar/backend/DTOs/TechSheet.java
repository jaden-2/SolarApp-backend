package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.enums.PanelType;

public record TechSheet(int power_w, double Vmax, PanelType type, double Imp, double Isc, double Voc){ }
