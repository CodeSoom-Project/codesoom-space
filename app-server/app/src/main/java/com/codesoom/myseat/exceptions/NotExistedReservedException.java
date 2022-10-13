package com.codesoom.myseat.exceptions;

/**
 * 존재 하지 않은 예약자가 회고를 작성 했을 때 발생하는 예외입니다.
 * */
public class NotExistedReservedException extends RuntimeException {
    public NotExistedReservedException() {
        super();
    }
}
