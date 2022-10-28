package com.codesoom.myseat.exceptions;

public class EmailTokenException
        extends RuntimeException {

    private static final String DEFAULT_MESSAGE
            = "올바르지 않은 요청입니다.";

    public EmailTokenException() {
        super(DEFAULT_MESSAGE);
    }

}
