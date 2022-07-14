package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SeatListResponse;
import com.codesoom.myseat.services.SeatListService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 좌석 목록 조회 컨트롤러
 */
@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatListController {
    private final SeatListService service;

    public SeatListController(SeatListService service) {
        this.service = service;
    }

    public List<SeatListResponse> seats() {
        return null;
    }
}
