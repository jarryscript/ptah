package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.common.BaseEntity
import com.github.jarryzhou.ptah.common.converter.StringListConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity

@Entity
class AuthorityMapping(
    var description: String? = null,
    @Convert(converter = StringListConverter::class) var authorities: MutableSet<String>? = mutableSetOf(),
    var role: String? = null
) : BaseEntity()
