package com.ptah.dto.project

import com.ptah.common.BaseDto
import com.ptah.entity.project.MileStone
import com.ptah.entity.project.ProjectStatus

class ProjectDto(name: String) : BaseDto<ProjectDto?>(){
    var mileStones: List<MileStone>?= emptyList()
    var status : ProjectStatus?=null
}
