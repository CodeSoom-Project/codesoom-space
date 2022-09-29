package com.codesoom.myseat.exceptions;

/**
 * 좌석 예약 내역을 찾을 수 없을 때 발생하는 예외
 */
public class SeatReservationNotFoundException extends RuntimeException {
    public SeatReservationNotFoundException(
            String message
    ) {
        super(message);
    }
}
