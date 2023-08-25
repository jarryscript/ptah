package com.ptah.entity.accounting;

import com.ptah.common.BaseEntity;
import com.ptah.entity.contract.ContractParty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {
    private String name;
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name="contract_party_id")
    private ContractParty contractParty;
}
