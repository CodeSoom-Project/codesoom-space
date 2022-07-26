package com.codesoom.myseat.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * 회원 레포지토리
 */
public interface UserRepository 
        extends CrudRepository<User, Long> {

    /**
     * 회원 정보를 저장한다.
     * 
     * @param user must not be {@literal null}.
     * @return 회원 정보
     */
    User save(User user);
}
