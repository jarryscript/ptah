package com.ptah.entity.user;

import com.ptah.entity.organization.Organization;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserNomination {
    @ManyToOne
    private User user;
    @ManyToOne
    private Organization organization;
    @Enumerated(EnumType.STRING)
    private ProjectRole ProjectRole;
}
