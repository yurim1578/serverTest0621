package com.project.metasu.util.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToYnConverter implements AttributeConverter<Boolean,String> {
    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute!=null&&attribute) ? "Y":"N";
    }
}
