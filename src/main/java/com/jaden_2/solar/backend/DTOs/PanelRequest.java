package com.jaden_2.solar.backend.DTOs;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PanelRequest {
    private String brand;
    private Integer power;
}
