package com.github.jarryzhou.ptah.dto.project

import com.github.jarryzhou.ptah.common.BaseDto
import com.github.jarryzhou.ptah.entity.project.MileStone
import com.github.jarryzhou.ptah.entity.project.ProjectStatus

class ProjectDto(
    var name: String,
    var mileStones: List<MileStone>? = emptyList(),
    var status: ProjectStatus? = ProjectStatus.PENDING
) : BaseDto()
