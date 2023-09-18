package com.ptah.entity.accounting

import com.ptah.common.BaseEntity
import com.ptah.entity.contract.Participant
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import lombok.*
import java.math.BigDecimal

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
class Account : BaseEntity() {
    private val name: String? = null
    private val balance: BigDecimal? = null

    @OneToOne
    private val accountOwner: Participant? = null
}
