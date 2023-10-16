package com.github.jarryzhou.ptah.common.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.*

@Converter
class StringListConverter : AttributeConverter<Set<String>, String> {
    override fun convertToDatabaseColumn(attribute: Set<String>): String = attribute.joinToString(SPLIT_CHAR)
    override fun convertToEntityAttribute(dbData: String): Set<String> = dbData.split(SPLIT_CHAR).toSet()

    companion object {
        private const val SPLIT_CHAR = ","
    }
}
