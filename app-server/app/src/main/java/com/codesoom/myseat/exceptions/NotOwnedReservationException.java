package com.codesoom.myseat.exceptions;

public class NotOwnedReservationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "본인이 소유한 예약이 아닙니다.";

    public NotOwnedReservationException() {
        super(DEFAULT_MESSAGE);
    }

    public NotOwnedReservationException(String message) {
        super(message);
    }
}
