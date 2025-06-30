package com.jaden_2.solar.backend.DTOs.sheets;

import jakarta.persistence.Embeddable;

@Embeddable
public record WireSpec(String SWG, double diameter_mm, double maxCurrent){}
