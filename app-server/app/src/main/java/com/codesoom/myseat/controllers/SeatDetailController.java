package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.SeatDetailResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.SeatDetailService;
import com.codesoom.myseat.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 상세 조회 컨트롤러
 */
@RestController
@RequestMapping("/seat")
@CrossOrigin
@Slf4j
public class SeatDetailController {
    private final SeatDetailService seatDetailservice;
    private final UserService userService;

    public SeatDetailController(
            SeatDetailService seatDetailservice, 
            UserService userService
    ) {
        this.seatDetailservice = seatDetailservice;
        this.userService = userService;
    }

    /**
     * 좌석 상세 조회 후 상태코드 200을 응답한다.
     *
     * @param seatNumber 조회할 좌석 번호
     * @return 좌석 예약 정보
     */
    @GetMapping("{seatNumber}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public SeatDetailResponse seatDetail(
            @PathVariable int seatNumber
    ) {
        log.info("seatNumber: " + seatNumber);
        
        String email = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getEmail();
        log.info("email: " + email);
        
        User user = userService.findUser(email);

        return seatDetailservice.seatDetail(seatNumber, user);
    }
}
