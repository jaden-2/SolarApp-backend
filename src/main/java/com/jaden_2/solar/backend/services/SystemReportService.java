package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.*;
import com.jaden_2.solar.backend.entities.*;
import com.jaden_2.solar.backend.repositories.ReportRepo;
import com.jaden_2.solar.backend.repositories.SystemReportsRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SystemReportService {
    private final SystemAnalysis analyser;

    // Reports
    private final SystemReportsRepo repo;
    private final ReportRepo reportRepo;
    //Specification services
    private final BatterySpecsService batterySS; //SS represent spec service
    private final PanelSpecsService panelSS;
    private final ControllerSpecsService controllerSS;
    private final BreakerSpecsService breakerSS;
    private final InverterSpecsService inverterSS;

    public SystemReport getReport(Integer id){
        return repo.findById(id).orElseThrow();
    }

    public List<SystemReport> getReports(Creator creator){
        return repo.findByCreator(creator);
    }

    public void saveReport(SystemReport report){
        repo.save(report);
        reportRepo.save(new Reports(report));
    }

    public void deleteReport(Integer id){
        repo.deleteById(id);
    }
    /*
    * A user won't be able to change safety features like DC breaker size
    * Components are dependent on one another, some patches requires resizing (especially safety components)
    * 1. When solar array is changes, the size of DC breaker and wire gauges needs to be recalculated.
    * 2. */
    /**
     * Patch report updates segments or components in the full system report
     * @param rBattery Updated battery request
     * @return An updated System report
     * */
    @Transactional
    public ReportDTO patchBatteryReport(@Valid BatteryDTO rBattery, Integer reportId){
        SystemReport report = repo.findById(reportId).orElseThrow();
        BatterySpecs originalBattery = report.getBatteryBank();
        BatterySpecs battery = batterySS.createSpec(rBattery, originalBattery.getBatteryCurrentCapacityAh());
        report.setBatteryBank(batterySS.updateSpec(battery, originalBattery));
        return new ReportDTO(report);
    }
    /**
     * Updates modifies the solar array component of the system report
     * Updating the solar array triggers changes to DC breaker and */
    @Transactional
    public ReportDTO patchArrayReport(@Valid ArrayDTO updatedArray, Integer reportId){
        SystemReport report = repo.findById(reportId).orElseThrow();
        ArraySpecs originalArray = report.getSolarArray();

        //Re-sizing dependent components
        // Based on changed panel and configuration, system automatically generates a new array spec
        ArraySpecs arraySpecs = panelSS.createSpec(updatedArray.getPanel(), updatedArray.getConfig(), originalArray.getCalculatePanelCapacity());
        BreakerSpecs breaker = analyser.sizeDCBreaker(arraySpecs.getElectricalProperties().Isc(), arraySpecs.getConfiguration().getParallel());
        var updatedBreaker = breakerSS.updateSpec(breaker, report.getDcBreaker()); // updates the DB breaker table;
        //------------------end----------------

        ArraySpecs updatedSpec = panelSS.updateSpec(arraySpecs, originalArray);
        // Because of the relationship between system report and array specs
        // Modifications to array spec table is implicitly saved to system report
        // Note that I am updating manually here but not saving to repository to prevent unnecessary queries to db
        report.setSolarArray(updatedSpec);
        report.setDcBreaker(updatedBreaker);
        return new ReportDTO(report);
    }
    @Transactional
    public ReportDTO patchControllerReport(@Valid ControllerDTO controllerDTO, Integer reportId){
        // Fetch the report that's to be updated
        SystemReport report = repo.findById(reportId).orElseThrow();
        // extract the initial specification
        ControllerSpecs originalController = report.getChargeController();
        // Using the DTO construct a template specification sheet
        ControllerSpecs controllerSpecs = controllerSS.createSpec(controllerDTO.id(), controllerDTO.config(), originalController.getCalculatedCapacity());
        // Using the template, update the original specification and save to db returning a copy of what's stored in db
        ControllerSpecs updatedSpec = controllerSS.updateSpec(controllerSpecs, originalController);
        // Without having to fetch the updated report from db, update the report with changed value and return
        report.setChargeController(updatedSpec);
        return new ReportDTO(report);
    }

    @Transactional
    public ReportDTO patchInverterReport(@Valid InverterDTO inverter, Integer reportId){
        SystemReport report = repo.findById(reportId).orElseThrow();
        InverterSpecs originalInverter = report.getInverter();

        InverterSpecs updateTemplate = inverterSS.createSpec(inverter.id(), inverter.config(), originalInverter.getCalculatedInverterCapacityKva());
        var updatedInverter = inverterSS.updateSpec(updateTemplate, originalInverter);

        report.setInverter(updatedInverter);
        return new ReportDTO(report);
    }
    @Transactional
    public ReportDTO updateReport(@Valid ReportDTO updatedReport, Integer reportId){
        if (!reportId.equals(updatedReport.getReportId())) throw new RuntimeException("Report update mismatch");
        SystemReport report = repo.findById(reportId).orElseThrow();
        var updateReport = report.updateReport(updatedReport);
        repo.save(updateReport);
        reportRepo.save(new Reports(updateReport));

        return new ReportDTO(updateReport);
    }
}
