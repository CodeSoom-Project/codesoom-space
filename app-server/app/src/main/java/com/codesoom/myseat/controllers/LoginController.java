package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.dto.LoginResponse;
import com.codesoom.myseat.services.UserService;
import com.codesoom.myseat.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인 컨트롤러
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(
        origins = "https://codesoom-project.github.io",
        allowedHeaders = "*",
        allowCredentials = "true")
@Slf4j
public class LoginController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public LoginController(
            UserService userService, 
            JwtUtil jwtUtil,
            AuthenticationManager authManager
    ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    /**
     * 사용자 인증 후 상태코드 200과 토큰을 응답한다.
     * 
     * @param req 로그인 정보
     * @return 토큰
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest req) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("로그인 실패");
        }
        
        UserDetails userDetails = userService.loadUserByUsername(req.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return toResponse(token);
    }

    /**
     * 응답 정보를 반환한다.
     *
     * @param token 토큰
     * @return 응답 정보
     */
    private LoginResponse toResponse(String token) {
        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
