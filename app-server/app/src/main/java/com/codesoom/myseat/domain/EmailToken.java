package com.codesoom.myseat.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EmailToken {

    private static final long TOKEN_EXPIRATION_MINUTES = 10L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2"
    )
    private String id;

    private LocalDateTime expirationDate;

    private boolean expired;

    private Long userId;

    /**
     * 주어진 회원 id에 대해 생성된 이메일 인증 토큰을 반환합니다.
     *
     * @param userId 회원 id
     * @return 이메일 인증 토큰
     */
    public static EmailToken createEmailToken(Long userId) {
        EmailToken token = new EmailToken();
        token.expirationDate = LocalDateTime.now()
                .plusMinutes(TOKEN_EXPIRATION_MINUTES);
        token.expired = false;
        token.userId = userId;

        return token;
    }

    /**
     * 토큰을 만료상태로 변경합니다.
     */
    public void setTokenExpired() {
        this.expired = true;
    }

}
