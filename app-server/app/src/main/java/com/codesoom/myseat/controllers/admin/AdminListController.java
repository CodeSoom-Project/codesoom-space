package com.codesoom.myseat.controllers.admin;


import com.codesoom.myseat.dto.AdminListResponse;
import com.codesoom.myseat.services.admin.AdminListService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 관리자 목록 조회 컨트롤러
 */
@CrossOrigin
@RequestMapping
@RestController
public class AdminListController {

    private final AdminListService adminService;

    public AdminListController(AdminListService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/admin/reservations")
    public List<AdminListResponse> reservations() {
        return AdminListResponse.of(adminService.reservations());
    }
}
