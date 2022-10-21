package com.codesoom.myseat.controllers;

import com.codesoom.myseat.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 컨트롤러에서 발생하는 예외를 처리한다.
 */
@ResponseBody
@ControllerAdvice
public class ControllerErrorAdvice {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound() {
        return "존재하지 않는 회원입니다.";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationFailureException.class)
    public String handleAuthenticationFailure() {
        return "로그인에 실패했습니다.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SeatNotFoundException.class)
    public String handleSeatNotFound() {
        return "존재하지 않는 좌석입니다.";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SeatListNotFoundException.class)
    public String handleSeatListNotFound() {
        return "좌석 목록이 존재하지 않습니다.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyHaveSeatException.class)
    public String handleAlreadyHaveSeat() {
        return "당일 예약 내역이 존재합니다.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyReservedException.class)
    public String handleAlreadyReserved() {
        return "방문 일자에 대한 예약 내역이 이미 존재합니다.";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public String handleReservationNotFoundException(ReservationNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotOwnedReservationException.class)
    public String handleNotRegisteredReservation(NotOwnedReservationException e) {
        return e.getMessage();
    }
    
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(RetrospectiveNotFoundException.class)
    public String handleRetrospectiveNotFound() {
        return "회고 조회에 실패했습니다.";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException.class)
    public String handleDuplicatedEmail() {
        return "이미 가입된 이메일입니다. 다른 이메일로 회원 가입을 해주세요";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyPostedRetrospectiveException.class)
    public String handleAlreadyPostedRetrospective() {
        return "이미 회고를 작성했습니다.";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ContentTooLongException.class)
    public String handleContentTooLong() {
        return "입력값 길이가 너무 깁니다. 1000자 이하로 입력해주세요.";
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ContentTooShortException.class)
    public String handleContentTooShort() {
        return "입력값 길이가 너무 짧습니다. 100자 이상으로  입력해주세요.";
    }
}
