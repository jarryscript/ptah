package com.gihub.jarryzhou.ptah.entity.contract

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
class Contract(
    @ManyToOne var partyA: Participant,

    @ManyToOne val partyB: Participant,
    val amount: BigDecimal,

    @Enumerated(value = EnumType.STRING) val type: ContractType,

    @Enumerated(value = EnumType.STRING) var status: ContractStatus
) : BaseEntity()
