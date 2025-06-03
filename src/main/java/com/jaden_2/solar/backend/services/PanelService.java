package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.DTOs.ArraySpecs;
import com.jaden_2.solar.backend.DTOs.PanelRequest;
import com.jaden_2.solar.backend.entities.Configuration;
import com.jaden_2.solar.backend.entities.Panel;
import com.jaden_2.solar.backend.entities.enums.SWG;
import com.jaden_2.solar.backend.repositories.PanelRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    private final PanelRepo repo;
    private final WireService wireRepo;
    public PanelService(PanelRepo repo, WireService gaugeRepo){
        this.repo = repo;
        this.wireRepo = gaugeRepo;
    }

    public List<Panel> getPanels(){
        return repo.findAll();
    }
    /**
     * Returns the solar panel user requests for
     * @param panelRequest Solar panel selected by user*/
    public Panel getPanelByBrandAndPower(PanelRequest panelRequest){
        return repo.findByBrandAndPower(panelRequest.getBrand(), panelRequest.getPower());
    }

    /**
     * Returns a detailed specification of solar array that best suites users requirement
     * @param panel Solar panel available in db
     * @param stringLength Number of series connection*/
    public ArraySpecs estimateArraySize(double arrayPower, Panel panel, int stringLength){
        // Number of panels required to meet array power_w requirement
        double numOfPanels = arrayPower / panel.getPower();
        numOfPanels = Math.ceil(numOfPanels);
        // Number of parallel connections
        int parallel = (int) Math.ceil(numOfPanels/stringLength);
        // Total current from solar array
        double current = parallel * panel.getImp();
        SWG gauge = wireRepo.getGaugeByCurrent(current);
        return new ArraySpecs(panel, new Configuration<>(stringLength, parallel), gauge, arrayPower);
    }
}
