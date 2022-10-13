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

}
