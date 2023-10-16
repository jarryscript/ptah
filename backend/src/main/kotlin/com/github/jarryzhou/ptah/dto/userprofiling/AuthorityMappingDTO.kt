package com.github.jarryzhou.ptah.dto.userprofiling

import com.github.jarryzhou.ptah.common.BaseDto

class AuthorityMappingDTO(
    var description: String,
    val authority: String,
    val role: String
) : BaseDto()
