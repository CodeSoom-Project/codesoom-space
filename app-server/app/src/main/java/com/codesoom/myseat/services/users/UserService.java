package com.codesoom.myseat.services.users;

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
     * id로 회원 조회에 성공하면 회원을 반환합니다.
     * 
     * @param id 아이디
     * @return 조회된 회원
     * @throws UserNotFoundException 회원 조회에 실패하면 던집니다.
     */
    public User findById(
            Long id
    ) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
    }

    /**
     * 이메일로 회원 조회에 성공하면 회원을 반환합니다.
     * 
     * @param email 이메일
     * @return 조회된 회원
     * @throws UserNotFoundException 회원 조회에 실패하면 던집니다.
     */
    public User findByEmail(
            String email
    ) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }

    /**
     * 주어진 이메일로 회원 조회에 성공하면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @param email 이메일
     * @return 회원 조회에 성공하면 true, 그렇지 않으면 false
     */
    public Boolean existByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
