package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationResponse;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import com.codesoom.myseat.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 예약 상세 조회 컨트롤러
 */
@CrossOrigin
@RequestMapping("/reservations")
@RestController
public class ReservationDetailController {
    
    private final UserService userService;
    private final ReservationDetailService service;

    public ReservationDetailController(
            UserService userService, 
            ReservationDetailService service
    ) {
        this.userService = userService;
        this.service = service;
    }

    /**
     * 예약 id와 회원 id로 예약 내용을 상세 조회합니다.
     *
     * @param principal 회원 인증 정보
     * @param reservationId 예약 id
     * @return 예약 정보
     * @throws ReservationNotFoundException 주어진 예약 id와 회원 id로 예약 정보를 찾지 못한 경우
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ReservationResponse reservation(
            @AuthenticationPrincipal UserAuthentication principal, 
            @PathVariable(name = "id") Long reservationId
    ) {
        User user = userService.findById(principal.getId());

        return new ReservationResponse(service.reservation(reservationId, user.getId()));
    }
    
}
