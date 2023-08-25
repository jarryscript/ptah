package com.ptah.entity.organization;

import com.ptah.entity.contract.ContractParty;
import com.ptah.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Organization extends ContractParty {
    private String businessCode;
}
