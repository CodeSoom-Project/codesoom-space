package com.codesoom.myseat.exceptions;

public class NotRegisteredReservation extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "등록되지 않은 예약입니다.";

    public NotRegisteredReservation() {
        super(DEFAULT_MESSAGE);
    }

    public NotRegisteredReservation(String message) {
        super(message);
    }
}
