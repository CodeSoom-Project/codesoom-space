package com.codesoom.myseat.filters;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class AuthenticationFilter extends BasicAuthenticationFilter {
    private final AuthenticationService authService;

    public AuthenticationFilter(
            AuthenticationManager authManager,
            AuthenticationService authService
    ) {
        super(authManager);
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("header: " + authHeader);

        if(authHeader != null) {
            String token = authHeader.substring("Bearer ".length());
            log.info("token: " + token);

            String email = authService.parseToken(token);
            log.info("email: " + email);

            List<Role> roles = authService.roles(email);
            log.info("roles: " + roles);

            Authentication authentication = new UserAuthentication(email, roles);
            log.info("authentication: " + authentication.getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            log.info("context: " + context.getAuthentication().getAuthorities().toString());
        }

        filterChain.doFilter(request, response);
    }
}
