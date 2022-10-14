package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.RetrospectiveService;
import com.codesoom.myseat.services.UserService;
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

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class RetrospectiveController {

    private final RetrospectiveService retrospectiveService;
    private final UserService userService;

    public RetrospectiveController(RetrospectiveService retrospectiveService, UserService userService) {
        this.retrospectiveService = retrospectiveService;
        this.userService = userService;
    }

    /**
     * 회고를 작성하고 상태 코드 204를 응답합니다.
     *
     * @param id 예약 Id
     * @param request 예약 폼에 입력된 데이터
     */
    @PostMapping("/{id}/retrospectives")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void writeRetrospective(
            @AuthenticationPrincipal UserAuthentication principal,
            @PathVariable final Long id,
            @RequestBody final RetrospectiveRequest request)
    {
        User user = userService.findById(principal.getId());
        String content = request.getRetrospective();

        retrospectiveService.createRetrospective(user, id, content);
    }

}
