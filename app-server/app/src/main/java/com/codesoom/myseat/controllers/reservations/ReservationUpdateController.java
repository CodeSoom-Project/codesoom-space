package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.dto.ReservationRequest;
import com.codesoom.myseat.exceptions.CannotUpdateCanceledReservationException;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 예약 수정 컨트롤러 */
@CrossOrigin
@RequestMapping("/reservations")
@RestController
public class ReservationUpdateController {

    private final ReservationUpdateService service;

    public ReservationUpdateController(ReservationUpdateService service) {
        this.service = service;
    }

    /**
     * 예약 수정 정보를 받아 수정합니다.
     *
     * @param principal 요청한 회원의 인증 정보
     * @param id        수정할 예약의 id
     * @param request   수정 데이터
     * @throws CannotUpdateCanceledReservationException 취소된 예약을 수정하려고 하면 던집니다.
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('VERIFIED_USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateReservation(@AuthenticationPrincipal UserAuthentication principal,
                                  @PathVariable Long id,
                                  @RequestBody ReservationRequest request) {
        service.updateReservation(principal.getId(), id, request);
    }

}
