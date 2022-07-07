package com.codesoom.myseat.controllers;

import com.codesoom.myseat.services.SeatReservationListService;
import org.springframework.web.bind.annotation.*;

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
}
