package com.ptah.dto.userprofiling

import com.ptah.common.BaseDto


class UserDto : BaseDto<UserDto?>() {
    var login: String? = null
    var nickname: String? = null
}
