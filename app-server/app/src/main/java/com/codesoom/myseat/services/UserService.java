package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 사용자 서비스
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    /**
     * 사용자 정보를 반환한다
     * 
     * @param username the username identifying the user whose data is required.
     *
     * @return 사용자 정보
     * @throws UsernameNotFoundException username이 존재하지 않는 경우 예외를 던진다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "[" + username + "]이 존재하지 않습니다."));
        return user;
    }
}
