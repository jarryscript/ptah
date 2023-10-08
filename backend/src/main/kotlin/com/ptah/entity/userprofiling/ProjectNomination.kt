package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import com.ptah.entity.project.Project
import jakarta.persistence.*

@Entity
class ProjectNomination(
    @ManyToOne var user: User,
    @ManyToOne var project: Project,
    @Enumerated(EnumType.STRING) var projectRole: ProjectRole
) : BaseEntity()
