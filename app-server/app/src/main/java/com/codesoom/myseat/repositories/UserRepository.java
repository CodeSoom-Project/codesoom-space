package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 레포지토리
 */
public interface UserRepository 
        extends JpaRepository<User, Long> {

    /**
     * 회원 정보를 저장한다.
     * 
     * @param user must not be {@literal null}.
     * @return 회원 정보
     */
    User save(User user);

    /**
     * 이메일로 회원 정보를 조회한다.
     * 
     * @param email 이메일
     * @return 회원 정보
     */
    Optional<User> findByEmail(String email);
}
