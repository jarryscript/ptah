package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import com.ptah.common.converter.StringListConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity

@Entity
class AuthorityMapping : BaseEntity() {
    var description: String? = null

    @Convert(converter = StringListConverter::class)
    var authorities: Set<String>? = null
    var role: String? = null
}
