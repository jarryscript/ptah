package com.ptah.entity.userprofiling;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrganizationNomination {
    @ManyToOne
    private User user;
    @ManyToOne
    private Organization organization;
    private String organizationRole;
}
