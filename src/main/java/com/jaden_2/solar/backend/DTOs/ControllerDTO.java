package com.jaden_2.solar.backend.DTOs;

import com.jaden_2.solar.backend.entities.Configuration;
import lombok.Data;


public record ControllerDTO (Integer id, Configuration config) {

}
