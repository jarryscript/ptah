package com.ptah.entity.acceptance;

import com.ptah.common.BaseEntity;
import com.ptah.entity.project.Project;
import jakarta.persistence.ManyToOne;

public class Acceptance extends BaseEntity {
    private String name;
    @ManyToOne
    private Project project;
    private String comments;
    private AcceptStatus status;
}
