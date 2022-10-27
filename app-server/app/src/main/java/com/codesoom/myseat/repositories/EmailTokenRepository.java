package com.codesoom.myseat.repositories;

import com.codesoom.myseat.domain.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;

/** 이메일 인증 리포지토리 */
public interface EmailTokenRepository
        extends JpaRepository<EmailToken, String> {
}
