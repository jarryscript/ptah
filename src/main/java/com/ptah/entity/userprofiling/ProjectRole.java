package com.ptah.entity.userprofiling;

public enum ProjectRole {
    PROJECT_MANAGER("项目经理"),
    PROJECT_SUPERVISION( "项目监理"),
    ;

    private String displayName;

    ProjectRole(String displayName) {
        this.displayName = displayName;
    }
}
