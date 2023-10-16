package com.github.jarryzhou.ptah.entity.acceptance

import com.ptah.common.BaseEntity
import com.ptah.entity.project.Project
import jakarta.persistence.ManyToOne

class Acceptance : BaseEntity() {
    val name: String? = null

    @ManyToOne
    val project: Project? = null
    val comments: String? = null
    val status: AcceptStatus? = null
}
