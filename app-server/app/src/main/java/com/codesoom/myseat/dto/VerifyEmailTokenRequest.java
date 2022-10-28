package com.codesoom.myseat.dto;

import lombok.Getter;

/** 이메일 인증 토큰 정보 */
@Getter
public class VerifyEmailTokenRequest {

    private String token;

    public VerifyEmailTokenRequest() {
    }

    public VerifyEmailTokenRequest(final String token) {
        this.token = token;
    }

}
