package com.codesoom.myseat.controllers.verification.email.verify;

import com.codesoom.myseat.dto.VerifyEmailTokenRequest;
import com.codesoom.myseat.services.verification.email.verify.VerifyEmailTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RequestMapping("/verification/email/verify")
@RestController
public class VerifyEmailTokenController {

    private final VerifyEmailTokenService service;

    public VerifyEmailTokenController(final VerifyEmailTokenService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void verifyEmailToken(
            @RequestBody VerifyEmailTokenRequest request) {
        log.info("이메일 인증 요청");
        service.verifyEmailToken(request);
    }

}
