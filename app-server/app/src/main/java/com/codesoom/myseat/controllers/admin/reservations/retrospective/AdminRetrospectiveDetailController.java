package com.codesoom.myseat.controllers.admin.reservations.retrospective;

import com.codesoom.myseat.domain.Retrospective;
import com.codesoom.myseat.dto.AdminRetrospectiveResponse;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 관리자 회고 조회 컨트롤러 */
@CrossOrigin
@RequestMapping("/admin/reservations")
@RestController
public class AdminRetrospectiveDetailController {

    private final RetrospectiveDetailService service;

    public AdminRetrospectiveDetailController(
            final RetrospectiveDetailService service
    ) {
        this.service = service;
    }

    /**
     * 예약 id로 회고 내용을 상세 조회합니다.
     *
     * @return 회고 내용
     */
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @RequestMapping("/{id}/retrospectives")
    public AdminRetrospectiveResponse retrospectives(
            @PathVariable(name = "id") final Long id
    ) {
        Retrospective retrospective = service.retrospective(id);

        return AdminRetrospectiveResponse.builder()
                .id(retrospective.getId())
                .content(retrospective.getContent())
                .createdDate(retrospective.getCreatedDate())
                .build();
    }

}
