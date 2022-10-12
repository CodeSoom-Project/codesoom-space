package com.codesoom.myseat.exceptions;

/**
 * 이미 예약했던 방문 일자에 예약을 시도할 때 발생하는 예외입니다.
 */
public class AlreadyReservedException extends RuntimeException {
    public AlreadyReservedException() {
        super();
    }
}
