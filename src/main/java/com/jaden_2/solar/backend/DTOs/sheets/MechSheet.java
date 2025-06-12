package com.jaden_2.solar.backend.DTOs.sheets;

import jakarta.persistence.Embeddable;

@Embeddable
public record MechSheet( double length_m,double width_m){}
