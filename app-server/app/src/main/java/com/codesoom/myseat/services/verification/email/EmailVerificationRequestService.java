package com.codesoom.myseat.services.verification.email;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/** 이메일 인증 요청 서비스 */
@Service
public class EmailVerificationRequestService {

    private final EmailTokenRepository repository;

    public EmailVerificationRequestService(
            final EmailTokenRepository repository) {
        this.repository = repository;
    }

    /**
     * 유저 id가 주어지면 해당 유저의 이메일 인증 토큰을 생성해 저장합니다.
     *
     * @return 생성된 이메일 인증 토큰
     */
    @Transactional
    public EmailToken requestEmailVerification(Long userId) {
        return repository.save(EmailToken.createEmailToken(userId));
    }

}
