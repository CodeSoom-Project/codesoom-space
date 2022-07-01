package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.dto.SeatAddRequest;
import com.codesoom.myseat.dto.SeatAddResponse;
import com.codesoom.myseat.services.SeatManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 관리 요청 컨트롤러
 */
@RestController
@RequestMapping("/seat")
public class SeatManagementController {
    private final SeatManagementService seatManagementService;

    public SeatManagementController(SeatManagementService seatManagementService) {
        this.seatManagementService = seatManagementService;
    }

    /**
     * 좌석을 추가한 후 상태코드 201과 좌석 정보를 응답한다.
     * 
     * @param seatAddRequest 추가할 좌석 정보
     * @return 좌석 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatAddResponse seatAdd(@RequestBody SeatAddRequest seatAddRequest) {
        Seat seat = seatManagementService.addSeat(seatAddRequest);
        return getResult(seat);
    }

    // TODO: 좌석 목록 조회

    // TODO: 좌석 수정

    // TODO: 좌석 삭제

    /**
     * 좌석 정보 DTO를 반환한다.
     *
     * @param seat 좌석 정보
     * @return 좌석 정보 DTO
     */
    private SeatAddResponse getResult(Seat seat) {
        return SeatAddResponse.builder()
                .number(seat.getNumber())
                .build();
    }
}
