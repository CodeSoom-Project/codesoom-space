package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 상세 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat")
public class SeatDetailController {
    private final SeatDetailService service;

    public SeatDetailController(SeatDetailService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SeatReservationResponse seatDetail(@PathVariable int seatNumber) {
        return null;
    }
}
