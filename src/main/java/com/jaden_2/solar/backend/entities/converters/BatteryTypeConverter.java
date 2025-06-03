package com.jaden_2.solar.backend.entities.converters;

import com.jaden_2.solar.backend.entities.enums.BatteryCategory;
import jakarta.persistence.AttributeConverter;

public class BatteryTypeConverter implements AttributeConverter<BatteryCategory, String> {
    @Override
    public String convertToDatabaseColumn(BatteryCategory batteryCategories) {
        return batteryCategories==null? null : batteryCategories.name();
    }

    @Override
    public BatteryCategory convertToEntityAttribute(String s) {
        try{
            return s==null? null : BatteryCategory.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
