package com.codesoom.myseat.exceptions;

/** 입력한 데이터의 길이가 너무 짧을 경우 발생하는 예외입니다. */
public class ContentTooShortException extends RuntimeException {
    public ContentTooShortException() {
        super();
    }
}
