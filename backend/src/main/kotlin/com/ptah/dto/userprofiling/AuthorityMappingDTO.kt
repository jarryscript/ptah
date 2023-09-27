package com.ptah.dto.userprofiling

import com.ptah.common.BaseDto
import com.ptah.entity.userprofiling.AuthorityMapping

class AuthorityMappingDTO(
    var description: String, val authority: String, val role: String
) : BaseDto()