package com.ptah.entity.contract;

import com.ptah.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Contract extends BaseEntity {

    @ManyToOne
    private Participant partyA;
    @ManyToOne
    private Participant partyB;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private ContractType type;
}
