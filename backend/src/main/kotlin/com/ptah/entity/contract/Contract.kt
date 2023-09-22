package com.ptah.entity.contract

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Contract : BaseEntity() {
    @ManyToOne
    val partyA: Participant? = null

    @ManyToOne
    val partyB: Participant? = null
    val amount: BigDecimal? = null

    @Enumerated(value = EnumType.STRING)
    val type: ContractType? = null
}
