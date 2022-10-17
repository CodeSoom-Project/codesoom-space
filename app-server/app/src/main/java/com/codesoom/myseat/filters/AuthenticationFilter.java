package com.codesoom.myseat.filters;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.exceptions.InvalidTokenException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.auth.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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

    private static final String TOKEN_PREFIX = "Bearer ";

    private final AuthenticationService authService;

    public AuthenticationFilter(
            AuthenticationManager authManager,
            AuthenticationService authService
    ) {
        super(authManager);
        this.authService = authService;
    }

    /**
     * http authorization 헤더의 토큰이 유효하면 SecurityContext에 인증 정보를 등록합니다.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            String accessToken = getAccessToken(request);

            Long id = authService.parseToken(accessToken);
            log.info("id: " + id);

            List<Role> roles = authService.roles(id);
            log.info("roles: " + roles);

            Authentication authentication = new UserAuthentication(id, roles);
            log.info("authentication: " + authentication.getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            log.info("context: " + context.getAuthentication().getAuthorities().toString());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 요청 헤더에 담긴 토큰을 반환합니다.
     *
     * @param request http 요청
     * @return accessToken
     * @throws InvalidTokenException 유효하지 않은 토큰일 경우 던짐
     */
    private String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("header: " + token);
        if (Strings.isBlank(token)) {
            throw new InvalidTokenException("토큰을 입력하세요.");
        }
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new InvalidTokenException("지원하지 않는 토큰 타입입니다.");
        }
        return token.substring(TOKEN_PREFIX.length());
    }

}
