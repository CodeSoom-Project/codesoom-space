package com.codesoom.myseat.controllers;

import com.codesoom.myseat.domain.SeatReservation;
import com.codesoom.myseat.dto.RetrospectiveRequest;
import com.codesoom.myseat.services.RetrospectiveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class RetrospectiveController {

    private final RetrospectiveService retrospectiveService;

    public RetrospectiveController(RetrospectiveService retrospectiveService) {
        this.retrospectiveService = retrospectiveService;
    }

    @PostMapping("/{id}/retrospectives")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void writeRetrospection(
            @PathVariable final SeatReservation id,
            @RequestBody final RetrospectiveRequest request)
    {
        retrospectiveService.createRetrospective(id.getId(), request);
    }

}
