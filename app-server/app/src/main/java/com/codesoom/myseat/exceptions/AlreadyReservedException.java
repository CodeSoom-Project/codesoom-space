package com.codesoom.myseat.exceptions;

/**
 * 방문 일자에 대한 예약 내역이 이미 존재하면 던집니다.
 */
public class AlreadyReservedException extends RuntimeException {
    public AlreadyReservedException() {
        super();
    }
}
