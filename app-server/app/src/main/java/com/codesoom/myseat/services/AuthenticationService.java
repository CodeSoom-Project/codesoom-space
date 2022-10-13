package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.AuthenticationFailureException;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(
            UserRepository userRepo, 
            RoleRepository roleRepo, 
            JwtUtil jwtUtil, 
            PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 주어진 회원 엔티티와 비밀번호로 인증에 성공하면, 생성된 토큰을 반환한다.
     * 
     * @param user 회원 엔티티
     * @param password 비밀번호
     * @return 생성된 토큰
     * @throws AuthenticationFailureException 인증에 실패하면 던진다.
     */
    public String login(
            User user,
            String password
    ) {
        if (!user.authenticate(password, passwordEncoder)) {
            throw new AuthenticationFailureException();
        }

        return jwtUtil.makeAccessToken(user.getId());
    }

    /**
     * 토큰을 파싱한다.
     * 
     * @param accessToken 토큰
     * @return 회원 id
     */
    public Long parseToken(
            String accessToken
    ) {
        return jwtUtil.parseAccessToken(accessToken);
    }

    /**
     * 권한 목록을 반환한다.
     * 
     * @param id 회원 id
     * @return 권한 목록
     */
    public List<Role> roles(
            Long id
    ) {
        return roleRepo.findAllByUserId(id);
    }
}
