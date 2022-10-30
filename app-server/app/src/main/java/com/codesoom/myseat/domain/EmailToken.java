package com.codesoom.myseat.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

    public EmailToken(final String id,
                      final LocalDateTime expirationDate,
                      final boolean expired,
                      final Long userId) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.expired = expired;
        this.userId = userId;
    }

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

    /**
     * 토큰의 만료 여부를 확인합니다.
     *
     * @return 토큰 만료 일시가 현재보다 이전이라면 true, 아니라면 false
     */
    public boolean isExpired() {
        return this.expirationDate
                .isBefore(LocalDateTime.now());
    }

}
