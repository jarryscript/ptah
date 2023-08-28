package com.ptah.entity.userprofiling;

import com.ptah.entity.contract.Participant;
import com.ptah.repository.userprofiling.ContactInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Organization extends Participant {
    private String businessCode;
    @OneToOne
    private ContactInfo contactInfo;
}
