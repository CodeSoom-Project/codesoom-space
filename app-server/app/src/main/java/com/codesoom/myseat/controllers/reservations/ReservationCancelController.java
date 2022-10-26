package com.codesoom.myseat.controllers.reservations;

import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationCancelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 좌석 예약 취소 컨트롤러
 */
@Slf4j
@CrossOrigin
@RequestMapping("/reservations")
@RestController
public class ReservationCancelController {

    private final ReservationCancelService cancelService;

    public ReservationCancelController(ReservationCancelService cancelService) {
        this.cancelService = cancelService;
    }

    /**
     * 좌석 예약을 취소한 후 상태코드 200을 응답한다.
     *
     * @param principal 회원 인증 정보
     * @param id 예약 취소할 좌석 번호
     */
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void cancelReservation(
            @AuthenticationPrincipal final UserAuthentication principal,
            @PathVariable final Long id) {
        log.debug("--------- 예약 취소 요쳥");
        log.debug("예약 id: {}", id);
        cancelService.cancelReservation(principal.getId(), id);
    }
    
}
