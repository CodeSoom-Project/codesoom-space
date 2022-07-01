package com.codesoom.myseat.controllers;

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

    // TODO: 좌석 추가
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeatAddResponse seatAdd(@RequestBody SeatAddRequest seatAddRequest) {
        return null;
    }

    // TODO: 좌석 목록 조회

    // TODO: 좌석 수정

    // TODO: 좌석 삭제
}
