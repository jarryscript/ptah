package com.gihub.jarryzhou.ptah.dto.userprofiling

import com.ptah.common.BaseDto

class AuthorityMappingDTO(
    var description: String,
    val authority: String,
    val role: String
) : BaseDto()
