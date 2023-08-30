package com.ptah.controller.project;

import com.ptah.auth.WithAuthority;
import com.ptah.dto.project.ProjectDto;
import com.ptah.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping
    @WithAuthority("platform-admin")
    public void create(@RequestBody ProjectDto projectDto){
        projectService.create(projectDto);
    }

    @PutMapping
    @WithAuthority("project-owner")
    public void update(@RequestBody ProjectDto projectDto){
        projectService.create(projectDto);
    }
}
