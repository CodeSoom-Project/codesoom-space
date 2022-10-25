package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationListResponse;
import com.codesoom.myseat.dto.ReservationResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationListService;
import com.codesoom.myseat.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/** 예약 목록 조회 컨트롤러 */
@CrossOrigin
@RequestMapping("/reservations")
@RestController
public class ReservationListController {

    private final UserService userService;
    
    private final ReservationListService service;

    public ReservationListController(
            UserService userService, 
            ReservationListService service
    ) {
        this.userService = userService;
        this.service = service;
    }

    /**
     * 요청한 회원의 모든 예약 목록을 조회합니다.
     *
     * @param principal 회원 인증 정보
     * @return 회원의 모든 예약 정보
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ReservationListResponse reservations(
            @AuthenticationPrincipal UserAuthentication principal
    ) {
        User user = userService.findById(principal.getId());

        return new ReservationListResponse(
                service.reservations(user.getId())
                .stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList())
        );
    }

}
