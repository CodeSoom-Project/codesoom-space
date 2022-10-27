package com.codesoom.myseat.controllers.admin.reservations;

import com.codesoom.myseat.dto.ReservationResponse;
import com.codesoom.myseat.exceptions.ReservationNotFoundException;
import com.codesoom.myseat.services.reservations.ReservationDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 관리자 예약 상세 조회 컨트롤러
 */
@CrossOrigin
@RequestMapping("/admin/reservations")
@RestController
public class AdminReservationDetailController {

    private final ReservationDetailService service;

    public AdminReservationDetailController(
            ReservationDetailService service
    ) {
        this.service = service;
    }

    /**
     * 예약 id로 예약 내용을 상세 조회합니다.
     *
     * @return 예약 정보
     * @throws ReservationNotFoundException 주어진 예약 id로 예약 정보를 찾지 못한 경우
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ReservationResponse reservation(
            @PathVariable(name = "id") Long id
    ) {
        return new ReservationResponse(service.reservation(id));
    }

}
