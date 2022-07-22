package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.SeatReservationResponse;
import com.codesoom.myseat.services.SeatReservationListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 좌석 예약 목록 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservations")
@CrossOrigin(origins = "https://codesoom-project.github.io/my-seat/")
public class SeatReservationListController {
    private final SeatReservationListService service;

    public SeatReservationListController(SeatReservationListService service) {
        this.service = service;
    }

    /**
     * 상태코드 200과 좌석 예약 목록을 응답한다.
     *
     * @return 좌석 예약 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatReservationResponse> seatReservations() {
        return toResponse(service.seatReservations());
    }

    /**
     * 좌석 예약 목록 DTO를 반환한다.
     *
     * @param data 좌석 예약 목록
     * @return 좌석 예약 목록 DTO
     */
    private List<SeatReservationResponse> toResponse(List<SeatReservation> data) {
        List<SeatReservationResponse> list = new ArrayList<>();
        SeatReservationResponse response;

        for(SeatReservation s : data) {
            response = SeatReservationResponse.builder()
                    .userName(s.getUserName())
                    .seatNumber(s.getSeatNumber())
                    .date(s.getDate())
                    .checkIn(s.getCheckIn())
                    .checkOut(s.getCheckOut())
                    .build();

            list.add(response);
        }

        return list;
    }
}
