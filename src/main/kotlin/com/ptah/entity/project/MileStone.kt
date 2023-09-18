package com.ptah.entity.project

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import lombok.Getter
import lombok.Setter
import java.time.Instant

@Entity
@Getter
@Setter
class MileStone : BaseEntity() {
    private val name: String? = null
    private val startTime: Instant? = null
    private val endTime: Instant? = null
    private val comments: String? = null

    @ManyToOne
    private val project: Project? = null
}
