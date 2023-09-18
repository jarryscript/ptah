package com.ptah.common.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.apache.commons.lang3.StringUtils
import java.util.*

@Converter
class StringListConverter : AttributeConverter<Set<String>, String> {
    override fun convertToDatabaseColumn(attribute: Set<String>): String {
        return Optional.ofNullable(attribute).map { value: Set<String>? -> java.lang.String.join(SPLIT_CHAR, value) }
            .orElse(StringUtils.EMPTY)
    }

    override fun convertToEntityAttribute(dbData: String): Set<String> {
        return Optional.ofNullable(dbData).map { data: String ->
            java.util.Set.of(*data.split(SPLIT_CHAR.toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray())
        }.orElseGet { emptySet() }
    }

    companion object {
        private const val SPLIT_CHAR = ","
    }
}
