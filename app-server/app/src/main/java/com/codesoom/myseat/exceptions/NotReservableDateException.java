package com.codesoom.myseat.exceptions;

/** 요청한 날짜가 예약이 불가한 경우 던짐 */
public class NotReservableDateException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "요청하신 예약일은 예약이 불가합니다.";

    public NotReservableDateException() {
        super(DEFAULT_MESSAGE);
    }

    public NotReservableDateException(String message) {
        super(message);
    }

}
