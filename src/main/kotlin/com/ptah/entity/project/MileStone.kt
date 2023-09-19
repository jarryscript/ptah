package com.ptah.entity.project

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.Instant

@Entity
class MileStone : BaseEntity() {
    var name: String? = null
    var startTime: Instant? = null
    var endTime: Instant? = null
    var comments: String? = null

    @ManyToOne
    var project: Project? = null
}
