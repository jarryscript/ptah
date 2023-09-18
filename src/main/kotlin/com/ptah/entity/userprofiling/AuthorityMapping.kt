package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import com.ptah.common.converter.StringListConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
class AuthorityMapping : BaseEntity() {
    private val description: String? = null

    @Convert(converter = StringListConverter::class)
    private val authorities: Set<String>? = null
    private val role: String? = null
}
