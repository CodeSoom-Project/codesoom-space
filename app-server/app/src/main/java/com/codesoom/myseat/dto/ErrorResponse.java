package com.codesoom.myseat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 응답 정보
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
