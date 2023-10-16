package com.github.jarryzhou.ptah.controller.userprofiling

import com.ptah.dto.userprofiling.AuthorityMappingDTO
import com.ptah.service.userprofiling.AuthorityService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthorizationController(var authorityService: AuthorityService) {

    @PostMapping
    fun addAuthorityToRole(@RequestBody authorityMappingData: AuthorityMappingDTO) {
        authorityService.addAuthorityMapping(authorityMappingData.authority, authorityMappingData.role)
    }

    @DeleteMapping
    fun removeAuthorityToRole(@RequestBody authorityMappingData: AuthorityMappingDTO) {
        authorityService.removeAuthorityMapping(authorityMappingData.authority, authorityMappingData.role)
    }
}
