package com.codesoom.myseat.dto;

import com.codesoom.myseat.domain.EmailToken;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/** 이메일 인증 요청에 대한 응답 데이터 */
public class EmailVerificationResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            timezone = "Asia/Seoul")
    private final LocalDateTime expirationDate;

    private final int expirationMinutes = 10;

    public EmailVerificationResponse(final EmailToken emailToken) {
        this.expirationDate = emailToken.getExpirationDate();
    }

}
