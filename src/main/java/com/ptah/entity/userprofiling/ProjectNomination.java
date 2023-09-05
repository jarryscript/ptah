package com.ptah.entity.userprofiling;

import com.ptah.common.BaseEntity;
import com.ptah.entity.project.Project;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProjectNomination extends BaseEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Project project;
    @Enumerated(EnumType.STRING)
    private ProjectRole projectRole;
}
