package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationCancelRequest;
import com.codesoom.myseat.dto.SeatReservationResponse;
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

    /**
     * 좌석 예약을 취소한 후 상태코드 204와 취소된 예약 정보를 응답한다.
     * @param seatNumber 예약 취소할 좌석 번호
     * @param request 좌석 예약 취소 요청 정보
     * @return 취소된 예약 정보
     */
    @DeleteMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public SeatReservationResponse cancelReservation(
            @PathVariable int seatNumber,
            @RequestBody SeatReservationCancelRequest request
    ) {
        return toResponse(service.cancelReservation(seatNumber, request));
    }

    /**
     * 취소된 예약 정보 DTO를 반환한다.
     *
     * @param data 취소된 예약 정보
     * @return 취소된 예약 정보 DTO
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
