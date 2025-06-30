package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.*;
import com.jaden_2.solar.backend.DTOs.sheets.WireDetails;
import com.jaden_2.solar.backend.entities.*;
import com.jaden_2.solar.backend.entities.inventory.Panel;
import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import com.jaden_2.solar.backend.entities.enums.DcCable;
import com.jaden_2.solar.backend.entities.enums.SWG;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SystemAnalysis {
    private final BatteryService batteryService;
    private final PanelService panelService;
    private final BreakerService breakerService;
    private final InverterService inverterService;
    private final ControllerService controllerService;

    public SystemReport analyseSystem(EstimatorRequest estimate, Creator creator){
        double batteryEnergy = estimate.getEnergy_wh() * estimate.getLoadOnBattery();
        BatterySpecs bank = sizeBattery(batteryEnergy, estimate.getDaysOfBackup(), estimate.getSystemVolt(), estimate.getBatteryType());
        bank.setCreator(creator);
        ArraySpecs arraySpecs = sizeSolarArray(estimate.getEnergy_wh(), estimate.getPreferredPanel(), estimate.getPsh(), estimate.getArraySeriesLength());
        arraySpecs.setCreator(creator);
        ControllerSpecs controllerSpecs = sizeController(estimate.getPreferredPanel(), arraySpecs.getConfiguration());
        controllerSpecs.setCreator(creator);
        InverterSpecs inverterSpecs = sizeInverter(estimate.getLoad_w(), estimate.getSystemVolt());
        inverterSpecs.setCreator(creator);
        BreakerSpecs dcBreaker = sizeDCBreaker(arraySpecs.getElectricalProperties().Imp(), arraySpecs.getConfiguration().getParallel());
        dcBreaker.setCreator(creator);
        List<WireDetails> wireDetails = sizeWireLenght(arraySpecs);
        Request request = new Request(estimate);
        return new SystemReport(bank, arraySpecs, dcBreaker, wireDetails, inverterSpecs, controllerSpecs, creator, request);
    }
    /**
     * Returns battery specification that fits user requirement based on what is available in db
     * @param autonomy Number of days battery can carry load_w without charge
     * @param category Type of battery
     * @param energy Estimated daily energy_wh consumed
     * @param systemVolts System voltage
     * @return BatterySpecs*/
    public BatterySpecs sizeBattery(double energy, double autonomy, int systemVolts, BatteryCategory category){
        double batteryCapacity = SystemEstimator.estimateBattery(energy, autonomy, systemVolts, category);
       return batteryService.evaluateBattery(batteryCapacity, category, systemVolts);
    }
    /**
     * Returns panel specification that fits users requirement based on user preferred panels size
     * @param energy Estimated daily energy_wh consumed
     * @param psh Peak sun hours in location
     * @param panelReq Preferred panel requested by user
     * @param stringLen Preferred connection configuration for series*/
    public ArraySpecs sizeSolarArray(double energy, PanelRequest panelReq, double psh, int stringLen){
        Panel panel = panelService.getPanelByBrandAndPower(panelReq);
        double arraySize = SystemEstimator.estimateArray(energy, panel.getType(), psh);
        return panelService.estimateArraySize(arraySize, panel, stringLen);
    }

    /**
     * Returns detailed specification of a charge controller that meets requirement
     * @param panelReq Preferred panel requested by user
     * @param arrayConfig Array configuration determined by system*/
    public ControllerSpecs sizeController(PanelRequest panelReq, Configuration arrayConfig) {
        Panel panel = panelService.getPanelByBrandAndPower(panelReq);
        double totalPower = arrayConfig.getSeries() * arrayConfig.getParallel() * panelReq.getPower(); // Solar array power_w in Wp
        double maxVolts = arrayConfig.getSeries() * panel.getVoc();
        return controllerService.evaluateController(totalPower, maxVolts);
    }
    /**
     * Returns the specification of an appropriately sized inverter from what's available in the database
     * @param sysVolts System voltage
     * @param load Total power_w of appliances*/
    public InverterSpecs sizeInverter(double load, int sysVolts){
        double capacity = SystemEstimator.estimateInverter(load)/1000;
        return inverterService.evaluateInverter(capacity, sysVolts);
    }
    /**
     * Returns the specification of an appropriate DC breaker using what's available in the database
     *@param isc Short circuit current of the selected panel
     *@param parallel Number of parallel connection of solar array*/
    public BreakerSpecs sizeDCBreaker(double isc, int parallel){
        double capacity = SystemEstimator.estimateDCBreaker(isc, parallel);
        return breakerService.evaluateBreaker(capacity, "DC");
    }

    /**
     * Estimates minimum lenght for each type of Dc cable to maintain that ensures power_w loss is minimum
     * @param arraySpecs The specification of solar array*/
    public List<WireDetails> sizeWireLenght(ArraySpecs arraySpecs){
        double power = arraySpecs.getConfiguration().getParallel() * arraySpecs.getConfiguration().getSeries() * arraySpecs.getElectricalProperties().power_w(); // Power of suggested array
        double current = arraySpecs.getElectricalProperties().Imp() * arraySpecs.getConfiguration().getParallel();
        SWG gauge = SWG.valueOf(arraySpecs.getWireSpecification().SWG());
        List<WireDetails> details = new ArrayList<>();
        for(DcCable cable : DcCable.values())
            details.add(new WireDetails(cable.name(),SystemEstimator.estimateWireLength(power, gauge, cable, current), SystemEstimator.powerLoss, arraySpecs.getWireSpecification()));
        return details;
    }
}
