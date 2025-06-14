package com.jaden_2.solar.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Configuration{
    private Integer series;
    private Integer parallel;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Integer getTotal(){
        return series * parallel;
    }
}
