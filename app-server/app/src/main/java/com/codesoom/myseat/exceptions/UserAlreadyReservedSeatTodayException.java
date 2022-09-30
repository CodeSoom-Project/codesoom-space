package com.codesoom.myseat.exceptions;

/**
 * 회원의 당일 예약 내역이 이미 있을 때 발생하는 예외
 */
public class UserAlreadyReservedSeatTodayException extends RuntimeException {
    public UserAlreadyReservedSeatTodayException(
            String message
    ) {
        super(message);
    }
}
