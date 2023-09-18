package com.ptah.entity.contract

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import lombok.Getter
import lombok.Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class Participant : BaseEntity() {
    private val name: String? = null
}
