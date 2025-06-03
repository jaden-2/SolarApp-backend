package com.jaden_2.solar.backend.entities.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

@Getter
public enum SWG {
    SWG7_0(12.7, 305.4),
    SWG6_0(11.786, 264.8),
    SWG5_0(10.973, 227.0),
    SWG4_0(10.16, 196.3),
    SWG3_0(9.449, 171.8),
    SWG2_0(8.230, 148.9),
    SWG0(8.23, 127.7),
    SWG1(7.62, 108.1),
    SWG2(7.01, 90.1),
    SWG3(6.401, 74.6),
    SWG4(5.893, 63.8),
    SWG5(5.385, 52.3),
    SWG6(4.877, 44.2),
    SWG7(4.47, 33.3),
    SWG8(4.064, 26.5),
    SWG9(3.658, 21.2);

    private double diameter;// unit mm
    private double maxCurrent;
    SWG(double diameter, double maxCurrent){
        this.diameter = diameter;
        this.maxCurrent = maxCurrent;
    }

    public static Optional<SWG> findByCurrent(double current){
        return Arrays.stream(SWG.values()).filter((swg->swg.maxCurrent >= current))
                .min(Comparator.comparingDouble(SWG::getMaxCurrent));
    }
}
