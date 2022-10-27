package com.codesoom.myseat.controllers.verification.email;

import com.codesoom.myseat.security.UserAuthentication;
import com.codesoom.myseat.services.verification.email.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/** 인증 이메일 전송 컨트롤러 */
@Slf4j
@CrossOrigin
//@RequestMapping("/verification/email")
//@RestController
public class SendVerificationEmailController {

    private final SendEmailService service;

    public SendVerificationEmailController(
            final SendEmailService service
    ) {
        this.service = service;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(
            @AuthenticationPrincipal final UserAuthentication principal
    ) throws IOException {
    }

}
