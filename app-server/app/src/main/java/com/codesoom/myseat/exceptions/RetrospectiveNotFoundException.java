package com.codesoom.myseat.exceptions;

/**
 * 회고를 찾지 못한 경우 던지는 예외
 */
public class RetrospectiveNotFoundException extends RuntimeException {
    public RetrospectiveNotFoundException() {
        super();
    }
}
