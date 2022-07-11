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
@CrossOrigin
public class SeatReservationCancelController {
    private final SeatReservationCancelService service;

    public SeatReservationCancelController(SeatReservationCancelService service) {
        this.service = service;
    }

    @DeleteMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(
            @PathVariable int seatNumber,
            @RequestBody SeatReservationCancelRequest request
    ) {
        // TODO: 예약 취소 서비스 호출
    }
}
