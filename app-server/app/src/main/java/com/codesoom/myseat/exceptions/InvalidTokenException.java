package com.codesoom.myseat.exceptions;

/** 유효하지 않은 토큰일 경우 던집니다. */
public class InvalidTokenException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "유효하지 않은 토큰입니다.";

    public InvalidTokenException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidTokenException(String message) {
        super(message);
    }

}
