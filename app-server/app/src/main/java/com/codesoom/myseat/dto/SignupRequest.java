package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 가입 요청 정보
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String name;

    private String email;

    private String password;
}
