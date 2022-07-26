package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.domain.UserRepository;
import com.codesoom.myseat.dto.SignupRequest;
import org.springframework.stereotype.Service;

/**
 * 회원 가입 서비스
 */
@Service
public class SignupService {
    private final UserRepository repository;

    public SignupService(UserRepository repository) {
        this.repository = repository;
    }

    public User signUp(SignupRequest request) {
        return null;
    }
}
