package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.common.BaseEntity
import com.github.jarryzhou.ptah.entity.project.Project
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne

@Entity
class ProjectNomination(
    @ManyToOne var user: User,
    @ManyToOne var project: Project,
    @Enumerated(EnumType.STRING) var projectRole: ProjectRole
) : BaseEntity()
