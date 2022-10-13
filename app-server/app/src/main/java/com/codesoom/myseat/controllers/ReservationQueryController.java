package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationListResponse;
import com.codesoom.myseat.dto.ReservationResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.ReservationQueryService;
import com.codesoom.myseat.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 예약 조회 컨트롤러
 */
@RequestMapping("/reservations")
@RestController
public class ReservationQueryController {

    private final UserService userService;
    private final ReservationQueryService service;

    public ReservationQueryController(UserService userService, ReservationQueryService service) {
        this.userService = userService;
        this.service = service;
    }

    /**
     * 예약 목록을 조회합니다.
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ReservationListResponse reservations(@AuthenticationPrincipal UserAuthentication principal) {
        User user = userService.findById(principal.getId());

        return new ReservationListResponse(
                service.reservations(user.getId())
                .stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList())
        );
    }

}
