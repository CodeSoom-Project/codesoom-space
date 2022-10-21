package com.codesoom.myseat.controllers.reservations.retrospectives;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.reservations.retrospectives.RetrospectiveUpdateService;
import com.codesoom.myseat.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
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

@CrossOrigin
@RestController
@RequestMapping("/reservations")
@Slf4j
public class RetrospectiveUpdateController {

    private final RetrospectiveUpdateService retrospectiveUpdateService;
    private final UserService userService;

    public RetrospectiveUpdateController(RetrospectiveUpdateService retrospectiveUpdateService, UserService userService) {
        this.retrospectiveUpdateService = retrospectiveUpdateService;
        this.userService = userService;
    }

    @PutMapping("/{id}/retrospectives")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRetrospective(
            @AuthenticationPrincipal final UserAuthentication principal,
            @PathVariable("id") final Long id,
            @RequestBody final RetrospectiveRequest request
    ) {

        User user = userService.findById(principal.getId());
        String content = request.getContent();

        retrospectiveUpdateService.update(id, user, content);
    }
}
