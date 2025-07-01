package com.jaden_2.solar.backend.DTOs;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ArrayDTO {
    @NotNull
    private PanelRequest panel;
    @Positive
    @Min(1)
    private Integer series;
}
