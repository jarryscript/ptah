package com.gihub.jarryzhou.ptah.dto.userprofiling

import com.ptah.common.BaseDto

class OrganizationNominationDTO(
    var organizationId: Long,
    var userId: Long,
    var organizationRole: String
) : BaseDto()
