package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 토큰 레포지토리
 */
public interface TokenRepository
        extends JpaRepository<Token, Long> {
    /**
     * 토큰을 저장한다.
     * 
     * @param token must not be {@literal null}. 저장할 토큰 정보
     * @return 토큰
     */
    Token save(Token token);
}
