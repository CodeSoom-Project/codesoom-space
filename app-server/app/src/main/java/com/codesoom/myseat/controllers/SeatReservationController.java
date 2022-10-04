package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.Seat;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.exceptions.AlreadyHaveSeatException;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.SeatReservationService;
import com.codesoom.myseat.services.SeatService;
import com.codesoom.myseat.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 요청 컨트롤러
 */
@RestController
@RequestMapping("/seat-reservation")
@CrossOrigin
@Slf4j
public class SeatReservationController {
    private final SeatReservationService reservationService;
    private final UserService userService;
    private final SeatService seatService;

    public SeatReservationController(
            SeatReservationService reservationService, 
            UserService userService,
            SeatService seatService
    ) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.seatService = seatService;
    }

    /**
     * 좌석을 예약한다.
     * @param number
     */
    @PostMapping("{number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void reserve(
            @PathVariable int number
    ) {
        log.info("number: " + number);
        String email = ((UserAuthentication) SecurityContextHolder.getContext().getAuthentication()).getEmail();
        User user = userService.findUser(email);

        if(user.status()) {
            throw new AlreadyHaveSeatException();
        }

        Seat seat = seatService.findSeat(number);

        if(seat.getStatus()) {
            throw new AlreadyReservedException();
        }

        reservationService.createReservation(user, seat);
    }
}
