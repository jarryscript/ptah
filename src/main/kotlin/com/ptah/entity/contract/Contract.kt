package com.ptah.entity.contract

import com.ptah.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ManyToOne
import lombok.Getter
import lombok.Setter
import java.math.BigDecimal

@Entity
@Getter
@Setter
class Contract : BaseEntity() {
    @ManyToOne
    private val partyA: Participant? = null

    @ManyToOne
    private val partyB: Participant? = null
    private val amount: BigDecimal? = null

    @Enumerated(value = EnumType.STRING)
    private val type: ContractType? = null
}
