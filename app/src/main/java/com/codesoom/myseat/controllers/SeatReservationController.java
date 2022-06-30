package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationRequest;
import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 관련 요청 컨트롤러
 */
@RestController
@RequestMapping("/seat")
public class SeatReservationController {
    private final SeatReservationService seatReservationService;

    public SeatReservationController(SeatReservationService seatReservationService) {
        this.seatReservationService = seatReservationService;
    }

    /**
     * 좌석을 예약한 후 상태코드 201과 예약 정보를 응답한다.
     *
     * @param id 예약할 좌석 id
     * @param seatReservationRequest 좌석 예약 요청 정보
     * @return 좌석 예약 정보
     */
    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public SeatReservationResponse reservationAdd(
            @PathVariable Long id,
            @RequestBody SeatReservationRequest seatReservationRequest
    ) {
        SeatReservation seatReservation
                = seatReservationService.addReservation(id, seatReservationRequest);
        return getResult(seatReservation);
    }

    // TODO: 좌석 예약 내역 조회

    // TODO: 좌석 예약 수정
    
    // TODO: 좌석 예약 취소

    /**
     * 좌석 예약 정보 DTO를 반환한다
     *
     * @param seatReservation 좌석 예약 정보
     * @return 좌석 예약 정보 DTO
     */
    private SeatReservationResponse getResult(SeatReservation seatReservation) {
        return SeatReservationResponse.builder()
                .userId(seatReservation.getUserId())
                .seatId(seatReservation.getSeatId())
                .date(seatReservation.getDate())
                .checkIn(seatReservation.getCheckIn())
                .checkOut(seatReservation.getCheckOut())
                .build();
    }
}
