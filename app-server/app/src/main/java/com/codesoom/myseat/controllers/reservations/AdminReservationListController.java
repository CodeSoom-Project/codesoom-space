package com.codesoom.myseat.controllers.admin.reservations;

import com.codesoom.myseat.dto.ReservationListResponse;
import com.codesoom.myseat.dto.ReservationResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationListService;
import com.codesoom.myseat.services.users.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * 예약 목록 조회 컨트롤러
 */
@CrossOrigin
@RequestMapping("/admin/reservations")
@RestController
public class AdminReservationListController {
    private final ReservationListService service;

    public AdminReservationListController(ReservationListService service) {
        this.service = service;
    }

    /**
     * 모든 회원의 예약 목록을 조회합니다.
     *
     * @return 모든 예약 정보
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @GetMapping
    public ReservationListResponse reservations() {
        return new ReservationListResponse(
                service.allReservations()
                        .stream()
                        .map(ReservationResponse::new)
                        .collect(Collectors.toList())
        );
    }
}
