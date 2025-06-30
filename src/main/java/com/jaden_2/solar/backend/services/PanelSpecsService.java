package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ComparisonResult;
import com.jaden_2.solar.backend.DTOs.PanelRequest;
import com.jaden_2.solar.backend.entities.ArraySpecs;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.Creator;
import com.jaden_2.solar.backend.entities.enums.SWG;
import com.jaden_2.solar.backend.entities.inventory.Panel;
import com.jaden_2.solar.backend.repositories.ArraySpecsRepo;
import com.jaden_2.solar.backend.repositories.PanelRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PanelSpecsService {
    private final ArraySpecsRepo repo;
    private final PanelRepo panelRepo;
    private final WireService wireService;
    public ArraySpecs getSpec(Integer id){
        return repo.findById(id).orElseThrow();
    }
    public ArraySpecs getSpecByUser(Integer id, Creator creator){
        return repo.findByArrayIdAndCreator(id, creator).orElseThrow();
    }

    public ArraySpecs createSpec(PanelRequest panelRequest, int series, double calcPower){
        Panel panel = panelRepo.findByBrandAndPower(panelRequest.getBrand(), panelRequest.getPower());
        double total =  Math.ceil(calcPower/panel.getPower());
        int parallel = (int) Math.ceil(total/series);
        double current = parallel * panel.getImp();
        SWG gauge = wireService.getGaugeByCurrent(current);
        return new ArraySpecs(panel, new Configuration(series, parallel), gauge,calcPower);
    }

    public void saveSpec(ArraySpecs spec){
        repo.save(spec);
    }

    public ArraySpecs updateSpec(ArraySpecs updatedArraySpec, ArraySpecs spec){
        spec.setBrand(updatedArraySpec.getBrand());
        spec.setModel(updatedArraySpec.getModel());
        spec.setElectricalProperties(updatedArraySpec.getElectricalProperties());
        spec.setMechanicalProperties(updatedArraySpec.getMechanicalProperties());
        spec.setConfiguration(updatedArraySpec.getConfiguration());
        spec.setWireSpecification(updatedArraySpec.getWireSpecification());

        repo.save(spec);
        return spec;
    }

    public void deleteSpec(int id){
        repo.deleteById(id);
    }

    public ComparisonResult compareSpecs(ArraySpecs modified, Integer id){
        ArraySpecs old = repo.findById(id).orElseThrow();
        double calcCap = old.getCalculatePanelCapacity();
        double newCap = modified.getConfiguration().getTotal() * modified.getElectricalProperties().power_w();
        double oldCap = old.getElectricalProperties().power_w() * old.getConfiguration().getTotal();
        double diff = (newCap - oldCap)/oldCap;

        String recommendation = newCap < calcCap? "New solar array configuration is smaller than recommended capacity of "+
                calcCap: "New solar array configuration is larger by "+diff+"%";
        String name = modified.getBrand() + " " + modified.getModel();
        return new ComparisonResult(name, diff, recommendation);
    }
}
