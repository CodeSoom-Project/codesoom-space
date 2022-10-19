package com.codesoom.myseat.exceptions;

/** 이미 회고를 작성했던 예약에 대해 회고 작성을 시도하면 발생하는 예외입니다.*/
public class AlreadyPostedRetrospectiveException extends RuntimeException {
    public AlreadyPostedRetrospectiveException() {
        super();
    }
}
