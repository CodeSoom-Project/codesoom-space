package com.codesoom.myseat.exceptions;

/**
 * 로그인에 실패하는 경우 발생하는 예외
 */
public class LoginFailureException extends RuntimeException {
    public LoginFailureException(String message) {
        super(message);
    }
}
