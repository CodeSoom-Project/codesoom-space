package com.codesoom.myseat.services.verification.email.verify;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.VerifyEmailTokenRequest;
import com.codesoom.myseat.exceptions.EmailTokenException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/** 인증 토큰 검증 서비스 */
@Service
public class VerifyEmailTokenService {

    private final EmailTokenRepository repository;
    private final UserRepository userRepository;

    public VerifyEmailTokenService(
            final EmailTokenRepository repository,
            final UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * 주어진 이메일 인증용 토큰을 찾을 수 있고, 만료되지 않으면 인증된 유저로 갱신합니다.
     *
     * @param request 인증 토큰
     * @throws EmailTokenException 주어진 토큰으로 찾을 수 없거나 만료된 경우 던짐
     * @throws UserNotFoundException 토큰에 해당하는 유저 id로 유저를 찾을 수 없는 경우 던짐
     */
    @Transactional
    public void verifyEmailToken(VerifyEmailTokenRequest request) {
        EmailToken token = repository.findById(request.getToken())
                .orElseThrow(EmailTokenException::new);
        if (token.isExpired()) {
            throw new EmailTokenException();
        }
        User user = userRepository.findById(token.getUserId())
                .orElseThrow(UserNotFoundException::new);
        user.verifyEmail();
    }

}
