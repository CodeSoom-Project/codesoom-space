package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 상세 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat")
@CrossOrigin(
        origins = "https://codesoom-project.github.io/my-seat/",
        allowedHeaders = "*",
        allowCredentials = "true")
public class SeatDetailController {
    private final SeatDetailService service;

    public SeatDetailController(SeatDetailService service) {
        this.service = service;
    }

    /**
     * 좌석 상세 조회 후 상태코드 200을 응답한다.
     *
     * @param seatNumber 조회할 좌석 번호
     * @return 좌석 예약 정보
     */
    @GetMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.OK)
    public SeatReservationResponse seatDetail(@PathVariable int seatNumber) {
        return toResponse(service.seatDetail(seatNumber));
    }

    /**
     * 응답 정보를 반환한다.
     *
     * @param data 좌석 예약 정보
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
