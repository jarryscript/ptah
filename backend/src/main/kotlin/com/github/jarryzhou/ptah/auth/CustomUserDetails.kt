package com.github.jarryzhou.ptah.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails : UserDetails {
    var authorities: Set<GrantedAuthority>? = null
    var internalPassword: String? = null
    var id: Long? = null

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities!!
    override fun getPassword(): String = internalPassword!!
    override fun getUsername(): String = ""
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
