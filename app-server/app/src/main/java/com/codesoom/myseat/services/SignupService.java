package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.SignupRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원 가입 서비스
 */
@Service
public class SignupService implements UserDetailsService {
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
        Role role = Role.ROLE_USER;
        
        User user = repository.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodePassword(request.getPassword()))
                .role(role)
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

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(
                        "[" + email + "]이 일치하는 회원을 찾을 수 없어서 조회에 실패했습니다."));
    }
}
