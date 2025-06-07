package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.ControllerSpecs;
import com.jaden_2.solar.backend.entities.inventory.ChargeController;
import com.jaden_2.solar.backend.repositories.ChargeControllerRepo;
import com.jaden_2.solar.backend.repositories.ControllerSpecsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ControllerSpecsService {
    private final ControllerSpecsRepo repo;

    public ControllerSpecs getChargeController(Integer id){
        return  repo.findById(id).orElseThrow();
    }

    public void createControllerSpecs(ControllerSpecs specs){
        assert specs!=null: "Cannot create charge controller specs of null";
        repo.save(specs);
    }

//    public ControllerSpecs updateControllerSpecs(ControllerSpecs updatedSpec, Integer id){
//        assert updatedSpec!=null:"Cannot update specs with null";
//        ControllerSpecs specs = repo.findById(id).orElseThrow();
//
//    }
}
