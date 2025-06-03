package com.jaden_2.solar.backend.services;

import com.jaden_2.solar.backend.entities.enums.SWG;
import com.jaden_2.solar.backend.exceptions.WireGaugeNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WireService {
    public SWG getGaugeByCurrent(double current){
        return SWG.findByCurrent(current).orElseThrow(()->new WireGaugeNotFoundException("Wire gauge not available"));
    }
}
