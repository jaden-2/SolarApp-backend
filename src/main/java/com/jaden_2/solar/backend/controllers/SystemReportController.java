package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.DTOs.*;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.services.SystemAnalysis;
import com.jaden_2.solar.backend.services.SystemReportService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/reports")
public class SystemReportController {

    @Autowired
    SystemAnalysis analysis;
    @Autowired
    SystemReportService service;
    @Autowired
    TemplateEngine engine;

    @GetMapping
    public ResponseEntity<?> getReports(@AuthenticationPrincipal UserDetails userDetails){
        var creator = new Creator(userDetails.getUsername());
        try{
            return ResponseEntity.ok(service.getReports(creator));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable("reportId") Integer id){
        try{
            return ResponseEntity.ok(service.getReport(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{reportId}/versions")
    public ResponseEntity<?> getReportVersions(@PathVariable("reportId") Integer reportId){
       try{
           return ResponseEntity.ok(service.getReportVersions(reportId));
       }catch (EntityNotFoundException e){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
       }
    }
    @GetMapping("/{reportId}/versions/{version}")
    public ResponseEntity<?> getReportVersion(@PathVariable("reportId") Integer reportId, @PathVariable("version") Integer version){
        try{
            return ResponseEntity.ok(service.getReportVersion(version));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/{reportId}/pdf")
    public void generatePdfReport(HttpServletResponse response, @PathVariable("reportId") Integer reportId) throws RuntimeException, IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        ReportDTO report = service.getReport(reportId);
        String gitHubLogo="";
        String gmailLogo="";
        String linkedInLogo="";
        Context context = new Context();
       try{
           gitHubLogo = classLoader.getResource("static/GitHub_Logo.png").toURI().toString();
           gmailLogo = classLoader.getResource("static/gmail.png").toURI().toString();
           linkedInLogo = classLoader.getResource("static/InBug-Black.png").toURI().toString();

           context.setVariable("github", gitHubLogo);
           context.setVariable("gmail", gmailLogo);
           context.setVariable("linkedIn", linkedInLogo);
       }catch (URISyntaxException e){
           System.out.println("Failed to load Logos");
       }


        context.setVariable("report", report);
        context.setVariable("createdAt", report.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")));
        context.setVariable("year", report.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy")));
        String htmlContent = engine.process("report-template", context);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report_"+reportId+".pdf");
        try(OutputStream os = response.getOutputStream()){
            PdfRendererBuilder renderer = new PdfRendererBuilder();
            renderer.useFastMode();
            renderer.withHtmlContent(htmlContent, null);

            renderer.toStream(os);
            renderer.run();
        }

    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport (@Valid @RequestBody EstimatorRequest estimate, @AuthenticationPrincipal UserDetails authUser){
        try{
            return ResponseEntity.ok(service.createAndSaveReport(estimate, authUser.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("{reportId}")
    public ResponseEntity<?> deleteReport(@PathVariable("reportId") Integer reportId){
       try{
           service.deleteReport(reportId);
           return ResponseEntity.noContent().build();
       } catch (EmptyResultDataAccessException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }
    @DeleteMapping("/{reportId}/versions/{version}")
    public ResponseEntity<?> deleteReportVersion(@PathVariable("reportId") Integer reportId, @PathVariable("version") Integer version){
        // reportId and versionId make a unique identifier, I can easily delete an item by id
        // I could also delete by reportId and versionId
        try{
            service.deleteReportVersion(version);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{reportId}/battery")
    public ResponseEntity<?> patchReportBattery(@Valid @RequestBody BatteryDTO batteryDTO, @PathVariable("reportId") Integer reportId){
        System.out.println(batteryDTO);
        System.out.println(reportId);
        try{
            return ResponseEntity.ok(service.patchBatteryReport(batteryDTO, reportId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{reportId}/inverter")
    public  ResponseEntity<?> patchReportInverter(@Valid @RequestBody InverterDTO inverterDTO, @PathVariable("reportId") Integer reportId){
        try{
            return ResponseEntity.ok(service.patchInverterReport(inverterDTO, reportId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PatchMapping("/{reportId}/controller")
    public ResponseEntity<?> patchReportController(@Valid @RequestBody ControllerDTO controllerDTO, @PathVariable("reportId") Integer reportId){
        try{
            return ResponseEntity.ok(service.patchControllerReport(controllerDTO, reportId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{reportId}/array")
    public ResponseEntity<?> patchReportArray(@Valid @RequestBody ArrayDTO arrayDTO, @PathVariable("reportId") Integer reportId){
        try{
            return ResponseEntity.ok(service.patchArrayReport(arrayDTO, reportId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<?> updateReport(@Valid @RequestBody ReportDTO reportDTO, @PathVariable("reportId") Integer reportId){
        try{
            return ResponseEntity.ok(service.updateReport(reportDTO, reportId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
