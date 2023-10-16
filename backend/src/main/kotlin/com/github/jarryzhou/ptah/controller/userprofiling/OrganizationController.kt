package com.github.jarryzhou.ptah.controller.userprofiling

import com.github.jarryzhou.ptah.dto.userprofiling.CreateOrganizationRequest
import com.github.jarryzhou.ptah.dto.userprofiling.CreateOrganizationResponse
import com.github.jarryzhou.ptah.service.userprofiling.OrganizationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/organization")
class OrganizationController(
    var organizationService: OrganizationService
) {

    @PostMapping
    fun createOrganization(@RequestBody createOrganizationRequest: CreateOrganizationRequest): CreateOrganizationResponse =
        organizationService.createOrganization(createOrganizationRequest)
}
