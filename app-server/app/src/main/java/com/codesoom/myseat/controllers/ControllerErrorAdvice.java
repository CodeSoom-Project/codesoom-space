package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.ErrorResponse;
import com.codesoom.myseat.exceptions.SeatAlreadyReservedException;
import com.codesoom.myseat.exceptions.SeatNotFoundException;
import com.codesoom.myseat.exceptions.SeatReservationNotFoundException;
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
    /**
     * 좌석을 찾지 못했을 때 에러메시지를 반환한다.
     *
     * @return 에러메시지
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SeatNotFoundException.class)
    public ErrorResponse handleSeatNotFound() {
        return new ErrorResponse("좌석을 찾을 수 없습니다.");
    }

    /**
     * 이미 예약된 좌석일 때 에러메시지를 반환한다.
     *
     * @return 에러메시지
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ErrorResponse handleSeatAlreadyReserved() {
        return new ErrorResponse("이미 예약된 좌석입니다.");
    }

    /**
     * 좌석 예약 내역을 찾지 못했을 때 에러메시지를 반환한다.
     *
     * @return 에러메시지
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SeatReservationNotFoundException.class)
    public ErrorResponse handleSeatReservationNotFound() {
        return new ErrorResponse("좌석 예약 목록을 찾을 수 없습니다.");
    }
}
