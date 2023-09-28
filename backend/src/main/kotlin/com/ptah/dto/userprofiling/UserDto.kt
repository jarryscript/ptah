package com.ptah.dto.userprofiling

import com.ptah.common.BaseDto


class UserDto(
    var login: String? = null, var nickname: String? = null, var authorities: List<String>? = emptyList()
) : BaseDto()
