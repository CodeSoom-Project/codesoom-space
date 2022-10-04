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
     * 주어진 엔티티를 저장하고, 저장된 엔티티를 반환한다.
     * 
     * @param user must not be {@literal null}.
     * @return 저장된 회원 엔티티
     */
    User save(User user);

    /**
     * 이메일로 회원 엔티티를 검색한다.
     * 
     * @param email 이메일
     * @return 회원 엔티티
     */
    Optional<User> findByEmail(String email);
}
