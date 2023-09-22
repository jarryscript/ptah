package com.ptah.auth

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Converter
class PasswordEncryptor : AttributeConverter<String?, String> {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
    override fun convertToDatabaseColumn(attribute: String?): String = passwordEncoder.encode(attribute)
    override fun convertToEntityAttribute(dbData: String): String = dbData
}
