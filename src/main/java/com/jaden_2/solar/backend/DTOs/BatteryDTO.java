package com.jaden_2.solar.backend.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BatteryDTO {
    @NotNull
    private Integer id;

}
