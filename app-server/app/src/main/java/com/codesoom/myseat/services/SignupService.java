package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.Role;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.RoleRepository;
import com.codesoom.myseat.repositories.UserRepository;
import com.codesoom.myseat.dto.SignupRequest;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원 가입 서비스
 */
@Service
@Slf4j
public class SignupService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public SignupService(
            UserRepository userRepo, 
            RoleRepository roleRepo,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 가입한 회원 정보를 반환한다.
     * 
     * @param request 가입할 회원 정보
     * @return 회원 정보
     */
    public User signUp(SignupRequest request) {
        log.info("name: " + request.getName());
        log.info("email: " + request.getEmail());
        log.info("password: " + request.getEmail());
        
        User user = userRepo.save(User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodePassword(request.getPassword()))
                .build());
        log.info("user name: " + user.getName());
        log.info("user email: " + user.getEmail());
        
        Role role = new Role(request.getEmail(), "USER");
        roleRepo.save(role);
        log.info("role: " + role.getRoleName());
        
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
