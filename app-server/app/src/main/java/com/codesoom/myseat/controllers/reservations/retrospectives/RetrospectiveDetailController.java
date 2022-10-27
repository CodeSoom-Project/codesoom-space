package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.Reservation;
import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.dto.RetrospectiveResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/** 회고 상세 조회 컨트롤러 */
@RestController
@RequestMapping("/reservations")
@CrossOrigin
@Slf4j
public class RetrospectiveDetailController {
    private final ReservationDetailService reservationDetailService;
    private final RetrospectiveDetailService retrospectiveDetailService;

    public RetrospectiveDetailController(
            ReservationDetailService reservationDetailService, 
            RetrospectiveDetailService retrospectiveDetailService
    ) {
        this.reservationDetailService = reservationDetailService;
        this.retrospectiveDetailService = retrospectiveDetailService;
    }

    /**
     * 회고 상세 조회에 성공하면 상태 코드 200과 함께 응답 정보를 반환합니다.
     * @param principal 인증 정보
     * @param id 예약 id
     * @return 응답 정보
     */
    @GetMapping("/{id}/retrospectives")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public RetrospectiveResponse retrospectiveDetail  (
            @AuthenticationPrincipal UserAuthentication principal,
            @PathVariable Long id
    ) {
        Reservation reservation = reservationDetailService.reservationOfUser(id, principal.getId());
        Retrospective retrospective = retrospectiveDetailService.retrospective(id);
        
        return toResponse(retrospective);
    }
    
    private RetrospectiveResponse toResponse(
            Retrospective retrospective
    ) {
        return RetrospectiveResponse.builder()
                .id(retrospective.getId())
                .content(retrospective.getContent())
                .build();
    }
}
