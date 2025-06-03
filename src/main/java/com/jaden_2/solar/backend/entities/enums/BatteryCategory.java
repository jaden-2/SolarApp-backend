package com.jaden_2.solar.backend.entities.enums;
/*
* A collection of the different categories of batteries*/
public enum BatteryCategory {
    FLOODED(0.5),
    TUBULAR(0.6),
    AGM(0.7),
    LITHIUM(0.85);

    private final double dob;
    BatteryCategory(double dob){
        this.dob = dob;
    }
    public double getDOB(){
        return this.dob;
    }
}
