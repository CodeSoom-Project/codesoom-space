package com.codesoom.myseat.controllers.signup;

import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.services.auth.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 가입 컨트롤러
 */
@RestController
@RequestMapping("/signup")
@CrossOrigin
@Slf4j
public class SignupController {
    private final SignupService service;

    public SignupController(
            SignupService service
    ) {
        this.service = service;
    }

    /**
     * 회원 가입을 한다.
     * 
     * @param request 회원 가입 폼에 입력된 데이터
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
            @RequestBody SignupRequest request
    ) {
        log.info("request: " + request.toString());

        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        service.createUser(name, email, password);
    }
}
