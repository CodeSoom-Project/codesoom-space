package com.codesoom.myseat.exceptions;

/**
 * 좌석을 찾을 수 없을 때 발생하는 예외
 */
public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String message) {
        super(message);
    }
}
