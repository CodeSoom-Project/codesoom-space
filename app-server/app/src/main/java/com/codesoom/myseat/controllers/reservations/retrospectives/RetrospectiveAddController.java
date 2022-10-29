package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.exceptions.AlreadyPostedRetrospectiveException;
import com.codesoom.myseat.exceptions.ContentTooLongException;
import com.codesoom.myseat.exceptions.ContentTooShortException;
import com.codesoom.myseat.exceptions.NotOwnedReservationException;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationAddService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveAddService;
import com.codesoom.myseat.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/reservations")
@Slf4j
public class RetrospectiveAddController {

    private final RetrospectiveAddService retrospectiveAddService;
    private final UserService userService;
    private final ReservationAddService reservationAddService;

    public RetrospectiveAddController(RetrospectiveAddService retrospectiveAddService, UserService userService, ReservationAddService reservationAddService) {
        this.retrospectiveAddService = retrospectiveAddService;
        this.userService = userService;
        this.reservationAddService = reservationAddService;
    }

    /**
     * 회고를 작성하고 상태 코드 204를 응답합니다.
     *
     * @param id      예약 Id
     * @param request 회고 폼에 입력된 데이터
     * @throws NotOwnedReservationException 예약자가 아닌 회원이 해당 예약에 대해 회고를 작성하려고 한다면 예외를 던집니다.
     * @throws ReservationNotFoundException 예약 조회에 실패하면 던집니다.
     * @throws ContentTooShortException     회고의 길이가 너무 짧을 경우 던집니다.
     * @throws ContentTooLongException      회고의 길이가 너무 길면 던집니다.
     */
    @PostMapping("/{id}/retrospectives")
    @PreAuthorize("isAuthenticated() and hasAuthority('VERIFIED_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void writeRetrospective(
            @AuthenticationPrincipal UserAuthentication principal,
            @PathVariable final Long id,
            @RequestBody final RetrospectiveRequest request) {
        User user = userService.findById(principal.getId());

        Reservation reservation = reservationAddService.findReservation(id);
        if (reservation.haveRetrospective()) {
            throw new AlreadyPostedRetrospectiveException();
        }

        String content = request.getContent();
        log.info("회고 요청: " + content);
        if (content.length() < 100) {
            throw new ContentTooShortException();
        }

        retrospectiveAddService.createRetrospective(user, id, content);
    }

}
