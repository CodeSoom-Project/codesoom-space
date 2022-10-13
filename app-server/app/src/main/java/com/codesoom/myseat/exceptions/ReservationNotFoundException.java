package com.codesoom.myseat.exceptions;

/** 예약을 찾지 못할 경우 던지는 예외 */
public class ReservationNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "예약을 찾지 못했습니다.";

    public ReservationNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ReservationNotFoundException(String message) {
        super(message);
    }

}
