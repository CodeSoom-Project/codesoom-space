package com.codesoom.myseat.controllers.seats;

import com.codesoom.myseat.dto.SeatAddRequest;
import com.codesoom.myseat.services.seats.SeatAddService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 추가 컨트롤러
 */
@RestController
@RequestMapping("/seat")
@CrossOrigin
@Slf4j
public class SeatAddController {
    private final SeatAddService service;

    public SeatAddController(
            SeatAddService service
    ) {
        this.service = service;
    }

    /**
     * 좌석을 추가한다.
     * 
     * @param request
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void addSeat(
            @RequestBody SeatAddRequest request
    ) {
        log.info("좌석 추가");
        int number = request.getNumber();
        
        service.createSeat(number);
    }
}
