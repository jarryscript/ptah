package com.github.jarryzhou.ptah.dto.userprofiling

import com.github.jarryzhou.ptah.common.BaseDto

class OrganizationNominationDTO(
    var organizationId: Long,
    var userId: Long,
    var organizationRole: String
) : BaseDto()
