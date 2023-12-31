package com.github.jarryzhou.ptah.repository.project

import com.github.jarryzhou.ptah.entity.project.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project?, Long?>
