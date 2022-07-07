package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatReservationListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 좌석 예약 목록 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservations")
@CrossOrigin
public class SeatReservationListController {
    private final SeatReservationListService service;

    public SeatReservationListController(SeatReservationListService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatReservationResponse> seatReservations() {
        return null;
    }
}
