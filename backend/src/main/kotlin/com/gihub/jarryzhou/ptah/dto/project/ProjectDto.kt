package com.gihub.jarryzhou.ptah.dto.project

import com.ptah.common.BaseDto
import com.ptah.entity.project.MileStone
import com.ptah.entity.project.ProjectStatus

class ProjectDto(
    var name: String,
    var mileStones: List<MileStone>? = emptyList(),
    var status: ProjectStatus? = ProjectStatus.PENDING
) : BaseDto()
