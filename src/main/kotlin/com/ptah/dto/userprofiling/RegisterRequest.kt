package com.ptah.dto.userprofiling

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class RegisterRequest {
    private val login: String? = null
    private val password: String? = null
}
