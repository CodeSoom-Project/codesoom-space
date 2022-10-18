package com.codesoom.myseat.exceptions;

/** 이미 가입된 이메일로 회원 가입을 시도할 경우 발생하는 예외입니다. */
public class DuplicatedEmailException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "이미 가입된 이메일입니다.";

    public DuplicatedEmailException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
