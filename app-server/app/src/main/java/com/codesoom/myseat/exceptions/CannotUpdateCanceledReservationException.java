package com.codesoom.myseat.exceptions;

/** 취소된 예약을 수정하려고 시도할 때 발생하는 예외입니다. */
public class CannotUpdateCanceledReservationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "취소된 예약은 수정할 수 없습니다. 취소되지 않은 예약으로 다시 시도해주세요";

    public CannotUpdateCanceledReservationException() {
        super(DEFAULT_MESSAGE);
    }

    public CannotUpdateCanceledReservationException(String message) {
        super(message);
    }
}
