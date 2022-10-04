package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 사용자 서비스
 */
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepo;

    public UserService(
            UserRepository userRepo
    ) {
        this.userRepo = userRepo;
    }

    /**
     * 이메일로 회원을 검색한다.
     * 
     * @param email
     * @return
     */
    public User findUser(
            String email
    ) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
