package com.jaden_2.solar.backend.entities.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaden_2.solar.backend.DTOs.sheets.WireDetails;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;
@Converter
public class WireDetailsTypeConverter implements AttributeConverter<List<WireDetails>, String> {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(List<WireDetails> wireDetails) {
        try{
           return mapper.writeValueAsString(wireDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WireDetails> convertToEntityAttribute(String s) {
        try{
            return mapper.readValue(s, new TypeReference<List<WireDetails>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
