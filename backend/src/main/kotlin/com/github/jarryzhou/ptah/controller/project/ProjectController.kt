package com.github.jarryzhou.ptah.controller.project

import com.github.jarryzhou.ptah.auth.WithAuthority
import com.github.jarryzhou.ptah.dto.project.ProjectDto
import com.github.jarryzhou.ptah.service.project.ProjectService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectController(var projectService: ProjectService) {
    @PostMapping
    @WithAuthority("platform_admin")
    fun create(@RequestBody projectDto: ProjectDto) {
        projectService.create(projectDto)
    }

    @PutMapping
    @WithAuthority("project_owner")
    fun update(@RequestBody projectDto: ProjectDto) {
        projectService.create(projectDto)
    }
}
