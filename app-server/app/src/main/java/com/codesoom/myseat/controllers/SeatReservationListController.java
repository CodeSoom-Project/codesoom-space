package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatReservationListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 좌석 예약 목록 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservations")
@CrossOrigin
@Slf4j
public class SeatReservationListController {
    private final SeatReservationListService listService;

    public SeatReservationListController(
            SeatReservationListService listService
    ) {
        this.listService = listService;
    }

    /**
     * 상태코드 200과 좌석 예약 목록을 응답한다.
     *
     * @return 좌석 예약 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatReservationResponse> seatReservations() {
        return toResponse(listService.seatReservations());
    }

    /**
     * 좌석 예약 목록 DTO를 반환한다.
     *
     * @param data 좌석 예약 목록
     * @return 좌석 예약 목록 DTO
     */
    private List<SeatReservationResponse> toResponse(
            List<SeatReservation> data
    ) {
        List<SeatReservationResponse> list = new ArrayList<>();
        SeatReservationResponse response;

        for(SeatReservation s : data) {
            response = SeatReservationResponse.builder()
                    .userName(s.getUser().getName())
                    .seatNumber(s.getSeat().getNumber())
                    .date(s.getDate())
                    .checkIn(s.getCheckIn())
                    .checkOut(s.getCheckOut())
                    .build();

            list.add(response);
        }

        return list;
    }
}
