package com.ptah.dto.userprofiling

import com.ptah.common.BaseDto
import com.ptah.entity.userprofiling.Organization

class CreateOrganizationResponse(var name: String? = null, var id: Long? = null) : BaseDto()
