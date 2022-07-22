package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 요청 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservation")
@CrossOrigin(
        origins = "https://codesoom-project.github.io/",
        allowedHeaders = "*",
        allowCredentials = "true")
public class SeatReservationController {
    private final SeatReservationService service;

    public SeatReservationController(SeatReservationService service) {
        this.service = service;
    }

    /**
     * 좌석을 예약한 후 상태코드 201과 예약 정보를 응답한다.
     *
     * @param seatNumber 예약할 좌석 번호
     * @param request 좌석 예약 요청 정보
     * @return 좌석 예약 정보
     */
    @PostMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public SeatReservationResponse addReservation(
            @PathVariable int seatNumber,
            @RequestBody SeatReservationRequest request
    ) {
        return toResponse(service.addReservation(seatNumber, request));
    }

    /**
     * 응답 정보를 반환한다.
     *
     * @param data 예약한 좌석 정보
     * @return 응답 정보
     */
    private SeatReservationResponse toResponse(SeatReservation data) {
        return SeatReservationResponse.builder()
                .userName(data.getUserName())
                .seatNumber(data.getSeatNumber())
                .date(data.getDate())
                .checkIn(data.getCheckIn())
                .checkOut(data.getCheckOut())
                .build();
    }
}
