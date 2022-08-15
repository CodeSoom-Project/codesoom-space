package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원 가입 서비스
 */
@Service
public class SignupService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public SignupService(
            UserRepository repository, 
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 가입한 회원 정보를 반환한다.
     * 
     * @param request 가입할 회원 정보
     * @return 회원 정보
     */
    public User signUp(SignupRequest request) {
        User user = repository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodePassword(request.getPassword()))
                .build());
        
        return user;
    }

    /**
     * 비밀번호를 암호화한다.
     * 
     * @param password 암호화 할 비밀번호
     * @return 암호화 된 비밀번호
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
