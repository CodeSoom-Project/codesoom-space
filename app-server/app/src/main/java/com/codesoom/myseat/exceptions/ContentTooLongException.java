package com.codesoom.myseat.exceptions;

/** 입력한 데이터의 길이가 너무 길 경우 발생하는 예외입니다. */
public class ContentTooLongException extends RuntimeException {
    public ContentTooLongException() {
        super();
    }
}
