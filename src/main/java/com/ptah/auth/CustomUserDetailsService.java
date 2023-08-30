package com.ptah.auth;

import com.ptah.common.Errors;
import com.ptah.entity.userprofiling.User;
import com.ptah.repository.userprofiling.AuthorityMappingRepository;
import com.ptah.repository.userprofiling.UserRepository;
import com.ptah.service.user.UserService;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).map(user->CustomUserDetails.builder()
                .authorities(userService.getAuthorities(user))
                .build()).orElseThrow(()->new UsernameNotFoundException(Errors.INVALID_CREDENTIALS.getMessage()));
    }


}
