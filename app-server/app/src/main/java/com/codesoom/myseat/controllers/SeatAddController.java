package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.dto.SeatAddRequest;
import com.codesoom.myseat.dto.SeatAddResponse;
import com.codesoom.myseat.services.SeatAddService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 추가 요청 컨트롤러
 */
@RestController
@RequestMapping("/seat")
@CrossOrigin(origins = "https://codesoom-project.github.io/my-seat")
public class SeatAddController {
    private final SeatAddService service;

    public SeatAddController(SeatAddService service) {
        this.service = service;
    }

    /**
     * 좌석을 추가한 후 상태코드 201과 좌석 정보를 응답한다.
     *
     * @param request 추가할 좌석 정보
     * @return 좌석 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatAddResponse addSeat(@RequestBody SeatAddRequest request) {
        return toResponse(service.addSeat(request));
    }

    /**
     * 응답 정보를 반환한다.
     *
     * @param data 추가한 좌석 정보
     * @return 응답 정보
     */
    private SeatAddResponse toResponse(Seat data) {
        return SeatAddResponse.builder()
                .number(data.getNumber())
                .build();
    }
}
