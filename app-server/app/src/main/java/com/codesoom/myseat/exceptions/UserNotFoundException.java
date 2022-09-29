package com.codesoom.myseat.exceptions;

/**
 * 회원을 찾을 수 없을 때 발생하는 예외
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(
            String message
    ) {
        super(message);
    }
}
