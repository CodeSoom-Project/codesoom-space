package com.codesoom.myseat.services;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
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
    
    public User findUser(
            String email
    ) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new SeatNotFoundException(
                        "[" + email + "] 조회 실패"));
    }
}
