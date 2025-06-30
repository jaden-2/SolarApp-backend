package com.jaden_2.solar.backend.entities.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jaden_2.solar.backend.entities.SystemReport;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ReportTypeConverter implements AttributeConverter<SystemReport, String> {
    ObjectMapper encoder = new ObjectMapper().registerModule(new JavaTimeModule());
    @Override
    public String convertToDatabaseColumn(SystemReport o) {

        try{
            return encoder.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SystemReport convertToEntityAttribute(String o) {
        try{
            return encoder.readValue(o, SystemReport.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
