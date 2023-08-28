package com.ptah.entity.contract;

import com.ptah.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy= InheritanceType.JOINED)
public class Participant extends BaseEntity {
    private String name;

}
