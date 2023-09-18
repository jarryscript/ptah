package com.ptah.entity.acceptance

import com.ptah.common.BaseEntity
import com.ptah.entity.project.Project
import jakarta.persistence.ManyToOne

class Acceptance : BaseEntity() {
    private val name: String? = null

    @ManyToOne
    private val project: Project? = null
    private val comments: String? = null
    private val status: AcceptStatus? = null
}
