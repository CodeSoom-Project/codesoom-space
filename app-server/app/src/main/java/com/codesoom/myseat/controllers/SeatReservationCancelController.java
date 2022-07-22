package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SeatReservationCancelRequest;
import com.codesoom.myseat.services.SeatReservationCancelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 취소 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservation")
@CrossOrigin(
        origins = "https://codesoom-project.github.io/my-seat/",
        allowedHeaders = "*",
        allowCredentials = "true")
public class SeatReservationCancelController {
    private final SeatReservationCancelService service;

    public SeatReservationCancelController(SeatReservationCancelService service) {
        this.service = service;
    }

    /**
     * 좌석 예약을 취소한 후 상태코드 204를 응답한다.
     *
     * @param seatNumber 예약 취소할 좌석 번호
     * @param request 좌석 예약 취소 요청 정보
     */
    @DeleteMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(
            @PathVariable int seatNumber,
            @RequestBody SeatReservationCancelRequest request
    ) {
        service.cancelReservation(seatNumber, request);
    }
}
