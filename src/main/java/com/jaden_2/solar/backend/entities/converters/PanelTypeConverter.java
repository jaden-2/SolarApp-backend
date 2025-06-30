package com.jaden_2.solar.backend.entities.converters;

import com.jaden_2.solar.backend.entities.enums.PanelType;
import jakarta.persistence.AttributeConverter;

public class PanelTypeConverter implements AttributeConverter<PanelType, String> {
    @Override
    public String convertToDatabaseColumn(PanelType panelType) {
        return panelType==null? null: panelType.name();
    }

    @Override
    public PanelType convertToEntityAttribute(String s) {
        try{
            return s==null? null: PanelType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
