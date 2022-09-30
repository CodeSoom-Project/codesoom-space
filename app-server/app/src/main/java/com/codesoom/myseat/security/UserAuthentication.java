package com.codesoom.myseat.security;

import com.codesoom.myseat.domain.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserAuthentication 
        extends AbstractAuthenticationToken {
    private final String email;

    public UserAuthentication(
            String email,
            List<Role> roles
    ) {
        super(authorities(roles));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String toString() {
        return "Authentication: " + email;
    }

    private static List<GrantedAuthority> authorities(
            List<Role> roles
    ) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
