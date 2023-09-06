package com.ptah.dto.userprofiling;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponse {
    private String accessToken;
}
