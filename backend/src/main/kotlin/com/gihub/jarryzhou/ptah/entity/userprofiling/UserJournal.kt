package com.gihub.jarryzhou.ptah.entity.userprofiling

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class UserJournal : BaseEntity() {
    @ManyToOne
    var user: User? = null
    var log: String ? = null
}
