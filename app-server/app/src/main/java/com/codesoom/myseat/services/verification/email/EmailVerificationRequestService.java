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

    private static final String SUBJECT = "코드숨 공부방 이메일 인증 안내입니다.";

    @Value("${email.auth.url}")
    private String URL;

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
        String content = emailVerificationContent(URL + token.getId());
        try {
            helper.send(email, SUBJECT, content);
        } catch (IOException e) {
            e.printStackTrace();
            throw new EmailSendFailureException();
        }

        return token;
    }

    private String emailVerificationContent(String href) {
        StringBuilder content = new StringBuilder();
        content.append("<div>");
        content.append("<p>안녕하세요.</p>");
        content.append("<p>코드숨 공부방을 이용해주셔서 감사합니다.</p>");
        content.append("<p>아래 링크를 클릭하여 이메일 인증을 완료해주세요.");
        content.append(String.format("<p><a href=\"%s\">이메일 인증하기</a></p>", href));
        content.append("</div>");
        return content.toString();
    }

}
