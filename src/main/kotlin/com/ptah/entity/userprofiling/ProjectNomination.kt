package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import com.ptah.entity.project.Project
import jakarta.persistence.*

@Entity
class ProjectNomination : BaseEntity() {
    @ManyToOne
    var  user: User? = null

    @ManyToOne
    var  project: Project? = null

    @Enumerated(EnumType.STRING)
    var  projectRole: ProjectRole? = null
}
