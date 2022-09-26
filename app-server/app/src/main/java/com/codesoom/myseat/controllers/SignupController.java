package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.services.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 가입 컨트롤러
 */
@RestController
@RequestMapping("/signup")
@CrossOrigin(
//        origins = "https://codesoom-project.github.io",
        origins = "*",
        allowedHeaders = "*",
        allowCredentials = "true")
public class SignupController {
    private final SignupService service;

    public SignupController(SignupService service) {
        this.service = service;
    }

    /**
     * 회원 가입 후 상태 코드 201을 응답한다.
     * 
     * @param request 회원 가입 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignupRequest request) {
        service.signUp(request);
    }
}
