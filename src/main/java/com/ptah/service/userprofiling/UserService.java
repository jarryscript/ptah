package com.ptah.service.userprofiling;

import cn.hutool.core.lang.Validator;
import com.ptah.common.Errors;
import com.ptah.common.exceptions.ApplicationException;
import com.ptah.dto.userprofiling.RegisterRequest;
import com.ptah.dto.userprofiling.UserDto;
import com.ptah.entity.userprofiling.AuthorityMapping;
import com.ptah.entity.userprofiling.Organization;
import com.ptah.entity.userprofiling.OrganizationNomination;
import com.ptah.entity.userprofiling.ProjectNomination;
import com.ptah.entity.userprofiling.ProjectRole;
import com.ptah.entity.userprofiling.User;
import com.ptah.repository.project.ProjectNominationRepository;
import com.ptah.repository.userprofiling.AuthorityMappingRepository;
import com.ptah.repository.userprofiling.OrganizationNominationRepository;
import com.ptah.repository.userprofiling.OrganizationRepository;
import com.ptah.repository.userprofiling.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AuthorityMappingRepository authorityMappingRepository;
    @Autowired
    private ProjectNominationRepository projectNominationRepository;
    @Autowired
    private OrganizationNominationRepository organizationNominationRepository;

    public UserDto register(RegisterRequest registerRequest) {
        validateRegisterRequest(registerRequest);
        return createUser(registerRequest);
    }

    private UserDto createUser(RegisterRequest registerRequest) {
        User savedUser = userRepository.save(User.builder().login(registerRequest.getLogin()).password(registerRequest.getPassword()).build());
        return UserDto.builder().login(savedUser.getLogin()).build();
    }

    private void validateRegisterRequest(RegisterRequest registerRequest) {
        Validator.validateNotEmpty(registerRequest.getLogin(), "");
        Validator.validateNotEmpty(registerRequest.getPassword(), "");
    }

    public Set<GrantedAuthority> getAuthorities(User user) {
        Set roles = new HashSet();
        roles.addAll(projectNominationRepository.findByUserId(user.getId()).stream().map(ProjectNomination::getProjectRole).map(ProjectRole::name).toList());
        roles.addAll(organizationNominationRepository.findByUserId(user.getId()).stream().map(OrganizationNomination::getOrganizationRole).toList());
        authorityMappingRepository.findByRoles(roles);
        return convertToGrantedAuthorities(authorityMappingRepository.findByRoles(roles));
    }

    private Set<GrantedAuthority> convertToGrantedAuthorities(Set<AuthorityMapping> authorityMappings) {
        return authorityMappings.stream().map(authorityMapping -> new SimpleGrantedAuthority(authorityMapping.getRole())).collect(Collectors.toSet());
    }

    public void assignUserToOrganization(Long userId, Long organizationId){
        if(organizationNominationRepository.exists(Example.of(OrganizationNomination.builder().build()))){
            throw ApplicationException.of(null);
        }
        organizationNominationRepository.save(OrganizationNomination.builder().build());
    }

    public void switchToOrganization(Long userId,Long organizationId){
        User user = userRepository.findById(userId).orElseThrow(() -> ApplicationException.of(Errors.USER_NOT_FOUND));
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(() -> ApplicationException.of(Errors.ORGANIZATION_NOT_FOUND));
        user.setCurrentOrganization(organization);
        userRepository.save(user);
    }
}
