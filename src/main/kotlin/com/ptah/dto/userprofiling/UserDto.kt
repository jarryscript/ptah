package com.ptah.dto.userprofiling

import com.ptah.common.BaseDto
import lombok.*

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserDto : BaseDto<UserDto?>() {
    private val login: String? = null
    private val nickname: String? = null
}
