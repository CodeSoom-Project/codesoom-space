package com.codesoom.myseat.exceptions;

/**
 * 좌석이 이미 사용중일 때 발생하는 예외
 */
public class SeatAlreadyInUseException extends RuntimeException {
    public SeatAlreadyInUseException(String message) {
        super(message);
    }
}
