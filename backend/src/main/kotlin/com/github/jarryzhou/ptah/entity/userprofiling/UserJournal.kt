package com.github.jarryzhou.ptah.entity.userprofiling

import com.github.jarryzhou.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne

@Entity
class UserJournal : BaseEntity() {
    @ManyToOne
    var user: User? = null
    var log: String ? = null
}
