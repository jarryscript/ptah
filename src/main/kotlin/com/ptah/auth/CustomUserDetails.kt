package com.ptah.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails : UserDetails {
    var authorities: Set<GrantedAuthority>? = null
    var password: String? = null

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities!!
    override fun getPassword(): String = password!!
    override fun getUsername(): String = ""
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
