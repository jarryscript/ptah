package com.ptah.entity.project;

import com.ptah.common.BaseEntity;
import com.ptah.entity.contract.Participant;
import com.ptah.repository.userprofiling.ContactInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Project extends BaseEntity {
    private String name;
    private Participant owner;
    private ProjectStatus status;
    @OneToOne
    private ContactInfo contactInfo;
}
