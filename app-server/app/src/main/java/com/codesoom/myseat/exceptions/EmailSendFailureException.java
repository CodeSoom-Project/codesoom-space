package com.codesoom.myseat.exceptions;

/** 이메일 전송에 실패한 경우 발생하는 예외입니다. */
public class EmailSendFailureException extends RuntimeException {

    private static final String DEFAULT_MESSAGE
            = "이메일 전송에 실패했습니다. 잠시 후 다시 시도해주세요.";

    public EmailSendFailureException() {
        super(DEFAULT_MESSAGE);
    }

    public EmailSendFailureException(String message) {
        super(message);
    }
}
