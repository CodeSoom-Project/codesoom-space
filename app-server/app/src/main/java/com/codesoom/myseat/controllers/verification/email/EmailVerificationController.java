package com.codesoom.myseat.controllers.verification.email;

import com.codesoom.myseat.dto.EmailVerificationResponse;
import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.verification.email.EmailVerificationRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** 이메일 인증 요청 컨트롤러 */
@CrossOrigin
@RequestMapping("/verification/email")
@RestController
public class EmailVerificationController {

    private final EmailVerificationRequestService service;

    public EmailVerificationController(
            final EmailVerificationRequestService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public EmailVerificationResponse requestEmailVerification(
            @AuthenticationPrincipal UserAuthentication principal) {
        return new EmailVerificationResponse(
                service.requestEmailVerification(principal.getId()));
    }

}
