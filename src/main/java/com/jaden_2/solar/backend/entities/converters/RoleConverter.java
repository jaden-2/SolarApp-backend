package com.jaden_2.solar.backend.entities.converters;

import com.jaden_2.solar.backend.entities.enums.Roles;
import jakarta.persistence.AttributeConverter;


public class RoleConverter implements AttributeConverter<Roles, String> {
    @Override
    public String convertToDatabaseColumn(Roles roles) {
        return roles==null?null:roles.name();
    }

    @Override
    public Roles convertToEntityAttribute(String s) {
        try{
            return s==null?null:Roles.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
