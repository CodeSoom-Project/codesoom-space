package com.codesoom.myseat.controllers.admin;

import com.codesoom.myseat.dto.AdminListResponse;
import com.codesoom.myseat.services.admin.AdminReservationListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 관리자 회원 예약 목록 조회 컨트롤러
 */
@CrossOrigin
@RequestMapping
@RestController
public class AdminReservationListController {

    private final AdminReservationListService adminReservationService;

    public AdminReservationListController(AdminReservationListService adminReservationService) {
        this.adminReservationService = adminReservationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/admin/reservations")
    public List<AdminListResponse> reservations() {
        return AdminListResponse.of(adminReservationService.reservations());
    }
}
