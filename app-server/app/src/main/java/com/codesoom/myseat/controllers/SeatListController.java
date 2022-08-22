package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.dto.SeatListResponse;
import com.codesoom.myseat.services.SeatListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 좌석 목록 조회 컨트롤러
 */
@RestController
@RequestMapping("/seats")
@CrossOrigin(
//        origins = "https://codesoom-project.github.io",
        origins = "*",
        allowedHeaders = "*",
        allowCredentials = "true")
public class SeatListController {
    private final SeatListService service;

    public SeatListController(SeatListService service) {
        this.service = service;
    }

    /**
     * 상태코드 200과 좌석 목록을 응답한다.
     *
     * @return 좌석 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SeatListResponse> seats() {
        return toResponse(service.seats());
    }

    /**
     * 좌석 목록 DTO를 반환한다.
     *
     * @param data 좌석 목록
     * @return 좌석 목록 DTO
     */
    private List<SeatListResponse> toResponse(List<Seat> data) {
        List<SeatListResponse> list = new ArrayList<>();
        SeatListResponse response;

        for(Seat s : data) {
            response = SeatListResponse.builder()
                    .seatNumber(s.getNumber())
                    .userName(s.getUserName())
                    .build();

            list.add(response);
        }

        return list;
    }
}
