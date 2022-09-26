package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatDetailResponse;
import com.codesoom.myseat.services.SeatDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 상세 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat")
@CrossOrigin(
//        origins = "https://codesoom-project.github.io",
        origins = "*",
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
    public SeatDetailResponse seatDetail(
            @PathVariable int seatNumber,
            @AuthenticationPrincipal User user
    ) {
        return service.seatDetail(seatNumber, user);
    }

//    /**
//     * 응답 정보를 반환한다.
//     *
//     * @param data 좌석 예약 정보
//     * @return 응답 정보
//     */
//    private SeatDetailResponse toResponse(Seat data) {
//        return SeatReservationResponse.builder()
//                .userName(data.getUserName())
//                .seatNumber(data.getSeatNumber())
//                .date(data.getDate())
//                .checkIn(data.getCheckIn())
//                .checkOut(data.getCheckOut())
//                .build();
//    }
}
