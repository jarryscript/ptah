package com.github.jarryzhou.ptah.dto.userprofiling

import com.github.jarryzhou.ptah.common.BaseDto

class UserDto(
    var login: String? = null,
    var nickname: String? = null,
    var authorities: List<String>? = emptyList()
) : BaseDto()
