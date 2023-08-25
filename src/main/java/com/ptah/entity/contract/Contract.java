package com.ptah.entity.contract;

import com.ptah.common.BaseEntity;
import com.ptah.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Contract extends BaseEntity {

    @ManyToOne
    private ContractParty partyA;
    @ManyToOne
    private ContractParty partyB;
    private BigDecimal amount;
}
