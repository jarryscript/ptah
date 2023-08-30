package com.ptah.common.converter;

import com.ptah.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Converter
public class StringListConverter implements AttributeConverter<Set<String>, String> {

    private static final String SPLIT_CHAR = ",";


    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return Optional.ofNullable(attribute).map(value -> String.join(SPLIT_CHAR, value)).orElse(StringUtils.EMPTY);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData).map(data -> Set.of(data.split(SPLIT_CHAR))).orElseGet(Collections::emptySet);
    }


}
