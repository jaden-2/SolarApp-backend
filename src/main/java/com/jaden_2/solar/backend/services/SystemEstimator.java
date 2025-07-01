package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import com.jaden_2.solar.backend.entities.enums.DcCable;
import com.jaden_2.solar.backend.entities.enums.PanelType;
import com.jaden_2.solar.backend.entities.enums.SWG;

/**
 * @author Sylvanus Jedidiah
 * @version 1.0
 * @implNote This class contains methods to estimate the size of various component that go into a solar system
 * @since 2025*/
public class SystemEstimator {
    //constants
    static final double misc = 1.1; // miscellaneous
    static final double inverterEff = 0.9; // efficiency of inverter
    static final double controllerFOS = 1.25; // factor of safety for charge controller
    static final double powerLoss = 0.02; // acceptable power_w loss
    static final double breakerFOS = 1.2; // factor of safety for breakers
    static final double inverterFOS = 1.2; // factor of safety for inverter
    /*--------------------end----------------------------------------*/


    /**
    * Estimates the battery capacity required to store energy_wh for number of days of autonomy
    * @param energy Estimated daily energy_wh consumption
    * @param daysOfAutonomy Number of days battery can carry load_w without charge
    * @param sysVolt system voltage [12, 24, 36, 48]
    * @param type Battery type [lithium, tubular, etc.]
    * @return battery capacity in Amps Hour*/
    public static double estimateBattery(double energy, double daysOfAutonomy, int sysVolt, BatteryCategory type){
        double batterSize = (energy / (inverterEff * type.getDOB())) * misc * daysOfAutonomy;
        return batterSize/sysVolt;
    }

    /**
     * Estimates the size of solar array, returns solar array size in Watts panel
     * @param energy Total daily energy_wh consumption
     * @param psh Peak sun hour
     * @param type Type of solar panel
     * @return Wp*/
    public static double estimateArray(double energy, PanelType type, Double psh){
        double PSH = psh==null? 5: psh;
        return (energy/type.getEfficiency())/PSH;
    }
    /**
     * Estimates the size of the charge controller
     * @param arrayPower Solar array power_w in Wp
     * @param maxPanelVolt Open circuit voltage of solar panel*/
    public static double estimateController(double arrayPower, double maxPanelVolt){
        return (arrayPower/maxPanelVolt)*  controllerFOS;
    }

    /**
     * Estimates the size of the DC breaker (amps)
     * @param arrayPower Solar array power_w in Wp
     * @param arrayVolts Total voltage from array
     * @return size of DC breaker in amps*/
    public static double estimateDCBreaker(double arrayPower, double arrayVolts){
        return (arrayPower/arrayVolts)*breakerFOS;
    }
    /**
     * Estimates the size of the DC breaker using short circuit current (amps)
     * @param Isc Short circuit current of panel
     * @param parallel Lenght of parallel connection of solar array
     * @return size of DC breaker in amps*/
    public static double estimateDCBreaker(double Isc, int parallel){
        return Isc * parallel * breakerFOS;
    }
    /**
     * Estimates the size of the AC breaker (amps)
     * @param arrayPower Solar array power_w in Wp
     * @param acVolts Inverter AC voltage
     * @return size of AC breaker in amps*/
    public static double estimateACBreaker(double arrayPower, int acVolts){
        return (arrayPower/acVolts) * breakerFOS;
    }
    /**
     * Estimates the size of the inverter to carry load_w
     * @param load Total power_w drawn by appliances
     * @return Capacity of inverter in volts-amps (va)*/
    public static double estimateInverter(double load){
        return (load * inverterFOS)/0.8;
    }
    public static double estimateWireLength(double arrayPower, SWG gauge, DcCable cable, double arrayCurrent){
        double powerDrop = arrayPower * (1- powerLoss);
        double area = Math.PI * (Math.pow(gauge.getDiameter()/1000, 2)) * 0.25; // area of a circle (PI.D^2)/4
        // lenght of cable to maintain prevent > 2% loss
        return (powerDrop * area)/ (cable.getResistivity() * Math.pow(arrayCurrent, 2) * 2);
    }
}
