package com.ptah.entity.accounting;

import com.ptah.common.BaseEntity;
import com.ptah.entity.contract.Participant;
import jakarta.persistence.Entity;
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
    private Participant accountOwner;
}
