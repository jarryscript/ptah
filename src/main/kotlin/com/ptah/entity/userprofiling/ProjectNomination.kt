package com.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import com.ptah.entity.project.Project
import jakarta.persistence.*
import lombok.*

@Entity
@Getter
@Setter
@Builder
class ProjectNomination : BaseEntity() {
    @ManyToOne
    private val user: User? = null

    @ManyToOne
    private val project: Project? = null

    @Enumerated(EnumType.STRING)
    private val projectRole: ProjectRole? = null
}
