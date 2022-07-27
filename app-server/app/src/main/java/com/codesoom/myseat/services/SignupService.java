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

    /**
     * 가입한 회원 정보를 반환한다.
     * 
     * @param request 가입할 회원 정보
     * @return 회원 정보
     */
    public User signUp(SignupRequest request) {
        return repository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build());
    }
}
