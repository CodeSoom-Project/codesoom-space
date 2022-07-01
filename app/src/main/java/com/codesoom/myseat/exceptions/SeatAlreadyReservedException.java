package com.codesoom.myseat.exceptions;

/**
 * 좌석이 이미 사용중일 때 발생하는 예외
 */
public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException(String message) {
        super(message);
    }
}
