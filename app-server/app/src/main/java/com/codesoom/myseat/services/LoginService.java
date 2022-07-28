package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.UserRepository;
import com.codesoom.myseat.dto.LoginRequest;
import org.springframework.stereotype.Service;

/**
 * 로그인 서비스
 */
@Service
public class LoginService {
    private final UserRepository repository;

    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    public String login(LoginRequest request) {
        return null;
    }
}
