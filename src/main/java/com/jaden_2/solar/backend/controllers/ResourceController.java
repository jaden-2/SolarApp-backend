package com.jaden_2.solar.backend.controllers;

import com.jaden_2.solar.backend.DTOs.EstimatorRequest;
import com.jaden_2.solar.backend.entities.inventory.*;
import com.jaden_2.solar.backend.services.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private BatteryService batteryService;
    @Autowired
    private InverterService inverterService;
    @Autowired
    private ControllerService controllerService;
    @Autowired
    private BreakerService breakerService;
    @Autowired
    private PanelService panelService;

    @GetMapping("/batteries")
    public ResponseEntity<List<Battery>> getBatteries(){
        return ResponseEntity.ok(batteryService.getBatteries());
    }
    @GetMapping("/batteries/{batteryId}")
    public  ResponseEntity<?> getBattery(@PathVariable("batteryId") Integer batteryId){
        try{
            return ResponseEntity.ok(batteryService.getBattery(batteryId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/inverters")
    public ResponseEntity<List<Inverter>> getInverters(){
        return ResponseEntity.ok(inverterService.getInverters());
    }
    @GetMapping("/inverters/{inverterId}")
    public  ResponseEntity<?> getInverter(@PathVariable("inverterId") Integer inverterId){
        try{
            return ResponseEntity.ok(inverterService.getInverterById(inverterId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/controllers")
    public ResponseEntity<List<ChargeController>> getControllers(){
        return ResponseEntity.ok(controllerService.getControllers());
    }
    @GetMapping("/controllers/{controllerId}")
    public  ResponseEntity<?> getController(@PathVariable("controllerId") Integer controllerId){
        try{
            return ResponseEntity.ok(controllerService.getControllerById(controllerId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/breakers")
    public ResponseEntity<List<Breaker>> getBreakers(){
        return ResponseEntity.ok(breakerService.getBreakers());
    }
    @GetMapping("/breakers/{breakerId}")
    public  ResponseEntity<?> getBreaker(@PathVariable("breakerId") Integer breakerId){
        try{
            return ResponseEntity.ok(breakerService.getBreaker(breakerId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/panels")
    public ResponseEntity<List<Panel>> getPanels(){
        return ResponseEntity.ok(panelService.getPanels());
    }
    @GetMapping("/panels/{panelId}")
    public  ResponseEntity<?> getPanel(@PathVariable("panelId") Integer panelId){
        try{
            return ResponseEntity.ok(panelService.getPanel(panelId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/template")
    public ResponseEntity<?> getTemplate(){
        return ResponseEntity.ok(new EstimatorRequest());
    }
}
