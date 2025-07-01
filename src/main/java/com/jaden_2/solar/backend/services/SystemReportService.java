package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.*;
import com.jaden_2.solar.backend.entities.*;
import com.jaden_2.solar.backend.repositories.ReportRepo;
import com.jaden_2.solar.backend.repositories.SystemReportsRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SystemReportService {
    private final SystemAnalysis analyser;

    // Reports
    private final SystemReportsRepo repo;
    private final ReportRepo reportRepo;
    private final CreatorService service;
    //Specification services
    private final BatterySpecsService batterySS; //SS represent spec service
    private final PanelSpecsService panelSS;
    private final ControllerSpecsService controllerSS;
    private final BreakerSpecsService breakerSS;
    private final InverterSpecsService inverterSS;

    public ReportDTO getReport(Integer id){
        return new ReportDTO(repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Report id does not exist")));
    }

    public List<ReportDTO> getReports(Creator creator){
        List<ReportDTO> reports = new ArrayList<>();
        List<SystemReport> result = repo.findAllByCreator(creator);

        if(result!=null)
                result.forEach(report-> reports.add(new ReportDTO(report)));
        else
            throw new EntityNotFoundException("Creator does not have reports saved");

        return  reports;
    }
    public ReportDTO getReportByRequest(Request request){
        return new ReportDTO(repo.findByRequest(request).orElseThrow(()-> new EntityNotFoundException("Request does not exist")));
    }

    public ReportDTO createAndSaveReport(EstimatorRequest request, String username) throws EntityNotFoundException{
        Creator creator = service.getUserByUsername(username);
        SystemReport report = analyser.analyseSystem(request, creator);

        try {
            saveReport(report);
        } catch (DataIntegrityViolationException e) {
            return getReportByRequest(new Request(request));
        }
        return new ReportDTO(report);
    }
    public void saveReport(SystemReport report) throws DataIntegrityViolationException {
        repo.save(report);
        reportRepo.save(new Reports(report));
    }

    public void deleteReport(Integer id) throws EmptyResultDataAccessException{
        repo.deleteById(id);
    }
    public void deleteReportVersion(Integer versionId) throws EmptyResultDataAccessException{
        reportRepo.deleteById(versionId);
    }
    /*
    * A user won't be able to change safety features like DC breaker size
    * Components are dependent on one another, some patches require resizing (especially safety components)
    * 1. When a solar array changes, the size of DC breaker and wire gauges needs to be recalculated.
    * 2. */
    /**
     * Patch report updates segments or components in the full system report
     * @param rBattery Updated battery request
     * @return An updated System report
     * */
    @Transactional
    public ReportDTO patchBatteryReport(BatteryDTO rBattery, Integer reportId) throws EntityNotFoundException{
        SystemReport report = repo.findById(reportId).orElseThrow(()->new EntityNotFoundException("Cannot patch a null report, report does not exist"));
        BatterySpecs originalBattery = report.getBatteryBank();
        BatterySpecs battery = batterySS.createSpec(rBattery.getId(), originalBattery.getBatteryCurrentCapacityAh(), report.getRequest().getRequestId().getSystemVolts());
        report.setBatteryBank(batterySS.updateSpec(battery, originalBattery));
        return new ReportDTO(report);
    }
    /**
     * Updates modifies the solar array component of the system report
     * Updating the solar array triggers changes to DC breaker and */
    @Transactional
    public ReportDTO patchArrayReport(@Valid ArrayDTO updatedArray, Integer reportId) throws EntityNotFoundException{
        SystemReport report = repo.findById(reportId).orElseThrow(()->new EntityNotFoundException("Cannot patch a null report, report does not exist"));
        ArraySpecs originalArray = report.getSolarArray();

        //Re-sizing dependent components
        // Based on a changed panel and configuration, the system automatically generates a new array spec
        ArraySpecs arraySpecs = panelSS.createSpec(updatedArray.getPanel(), updatedArray.getSeries(), originalArray.getCalculatePanelCapacity());
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
    public ReportDTO patchControllerReport(@Valid ControllerDTO controllerDTO, Integer reportId) throws EntityNotFoundException{
        // Fetch the report that's to be updated
        SystemReport report = repo.findById(reportId).orElseThrow(()-> new EntityNotFoundException("Cannot patch a null report, report does not exist"));
        // extract the initial specification
        ControllerSpecs originalController = report.getChargeController();
        // Using the DTO construct a template specification sheet
        ControllerSpecs controllerSpecs = controllerSS.createSpec(controllerDTO.id(), originalController.getCalculatedCapacity());
        // Using the template, update the original specification and save to db returning a copy of what's stored in db
        ControllerSpecs updatedSpec = controllerSS.updateSpec(controllerSpecs, originalController);
        // Without having to fetch the updated report from db, update the report with changed value and return
        report.setChargeController(updatedSpec);
        return new ReportDTO(report);
    }

    @Transactional
    public ReportDTO patchInverterReport(@Valid InverterDTO inverter, Integer reportId) throws EntityNotFoundException{
        SystemReport report = repo.findById(reportId).orElseThrow(()-> new EntityNotFoundException("Cannot patch a null report, report does not exist"));
        InverterSpecs originalInverter = report.getInverter();
        InverterSpecs updateTemplate = inverterSS.createSpec(inverter.id(), originalInverter.getCalculatedInverterCapacityKva(), report.getRequest().getRequestId().getSystemVolts());
        var updatedInverter = inverterSS.updateSpec(updateTemplate, originalInverter);
        report.setInverter(updatedInverter);
        return new ReportDTO(report);
    }
    @Transactional
    public ReportDTO updateReport(@Valid ReportDTO updatedReport, Integer reportId)throws RuntimeException{
        if (!reportId.equals(updatedReport.getReportId())) throw new RuntimeException("Report update mismatch");
        SystemReport report = repo.findById(reportId).orElseThrow(()->new EntityNotFoundException("Cannot update report that does not exist"));
        var updateReport = report.updateReport(updatedReport);
        repo.save(updateReport);
        reportRepo.save(new Reports(updateReport));

        return new ReportDTO(updateReport);
    }

    public List<ReportDTO> getReportVersions(Integer reportId){
        List<ReportDTO> reports = new ArrayList<>();
        var result = reportRepo.findAllByReportRef_reportId(reportId);

        if(result!=null) result.forEach(report->reports.add(new ReportDTO(report.getReport())));
        else throw new EntityNotFoundException("Creator has no saved entity");

        return reports;
    }

    public ReportDTO getReportVersion(Integer versionId){
        return new ReportDTO(repo.findById(versionId).orElseThrow(()-> new EntityNotFoundException("Report version does not exist")));
    }
}
