package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.exceptions.AlreadyReservedException;
import com.codesoom.myseat.exceptions.ContentTooLongException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationAddService;
import com.codesoom.myseat.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 요청 컨트롤러
 */
@RestController
@RequestMapping("/reservations")
@CrossOrigin
@Slf4j
public class ReservationAddController {
    private final ReservationAddService reservationAddService;
    private final UserService userService;

    public ReservationAddController(
            ReservationAddService reservationAddService, 
            UserService userService
    ) {
        this.reservationAddService = reservationAddService;
        this.userService = userService;
    }

    /**
     * 좌석을 예약하고 상태 코드 204를 응답합니다.
     * 
     * @param request 예약 폼에 입력된 데이터
     * @throws AlreadyReservedException 방문 일자에 대한 예약 내역이 이미 존재하면 던집니다.
     * @throws ContentTooLongException 계획의 길이가 너무 길면 던집니다.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void reserve(
            @AuthenticationPrincipal UserAuthentication principal,
            @RequestBody ReservationRequest request
    ) {
        User user = userService.findById(principal.getId());

        String date = request.getDate();
        String content = request.getContent();

        reservationAddService.createReservation(user, date, content);
    }
}
