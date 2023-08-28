package com.ptah.service.project;

import com.ptah.dto.project.ProjectDto;
import com.ptah.entity.project.Project;
import com.ptah.entity.userprofiling.ProjectNomination;
import com.ptah.repository.project.ProjectNominationRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectNominationRepository projectNominationRepository;

    public List<ProjectDto> getProjectsOfUser(Long userId) {
        return Optional.ofNullable(projectNominationRepository.findByUserId(userId)).orElseGet(Collections::emptyList).stream().map(ProjectNomination::getProject).map(project -> new ProjectDto().fromEntity(project)).collect(Collectors.toList());
    }

}
