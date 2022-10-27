package com.codesoom.myseat.services.verification.email;

import com.codesoom.myseat.domain.EmailToken;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.EmailSendFailureException;
import com.codesoom.myseat.exceptions.UserNotFoundException;
import com.codesoom.myseat.helpers.EmailHelper;
import com.codesoom.myseat.repositories.EmailTokenRepository;
import com.codesoom.myseat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

/** 이메일 인증 요청 서비스 */
@Service
public class EmailVerificationRequestService {

    private final EmailTokenRepository emailTokenRepository;
    private final UserRepository userRepository;
    private final EmailHelper helper;

    @Value("${email.auth.subject}")
    private static String SUBJECT;

    @Value("${email.auth.url}")
    private static String URL;

    public EmailVerificationRequestService(
            final EmailTokenRepository emailTokenRepository,
            final UserRepository userRepository,
            final EmailHelper helper
    ) {
        this.emailTokenRepository = emailTokenRepository;
        this.userRepository = userRepository;
        this.helper = helper;
    }

    /**
     * 이메일 인증 토큰 생성하여 인증 메일을 전송한 후 생성된 토큰을 반환합니다.
     *
     * @param userId 회원 id
     * @return 생성된 이메일 인증 토큰
     * @throws UserNotFoundException     회원 조회에 실패하면 던집니다.
     * @throws EmailSendFailureException 이메일 전송에 실패하면 던집니다.
     */
    @Transactional
    public EmailToken requestEmailVerification(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        EmailToken token = emailTokenRepository
                .save(EmailToken.createEmailToken(userId));

        String email = user.getEmail();
        String content = URL + token;

        try {
            helper.send(email, SUBJECT, content);
        } catch (IOException e) {
            e.printStackTrace();
            throw new EmailSendFailureException();
        }

        return token;
    }

}
