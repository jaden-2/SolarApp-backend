package com.jaden_2.solar.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration<T, K>{
    private T series;
    private K parallel;
}
