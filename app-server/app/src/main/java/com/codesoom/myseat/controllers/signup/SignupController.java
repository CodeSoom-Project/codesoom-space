package com.codesoom.myseat.controllers.signup;

import com.codesoom.myseat.dto.SignupRequest;
import com.codesoom.myseat.exceptions.DuplicatedEmailException;
import com.codesoom.myseat.services.auth.SignupService;
import com.codesoom.myseat.services.users.UserService;
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
    private final UserService userService;

    public SignupController(
            SignupService service,
            UserService userService
    ) {
        this.service = service;
        this.userService = userService;
    }

    /**
     * 회원 가입을 한다.
     * 
     * @param request 회원 가입 폼에 입력된 데이터
     * @throws DuplicatedEmailException 이미 가입된 이메일로 회원 가입을 시도하면 던집니다.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signUp(
            @RequestBody SignupRequest request
    ) {
        log.info("request: " + request.toString());

        String email = request.getEmail();
        if(isDuplicatedEmail(email)) {
            throw new DuplicatedEmailException();
        }
        
        String name = request.getName();
        String password = request.getPassword();

        service.createUser(name, email, password);
    }

    /**
     * 이미 가입된 이메일이면 true, 그렇지 않으면 false를 반환합니다.
     * 
     * @param email 이메일
     * @return 이미 가입된 이메일이면 true, 그렇지 않으면 false
     */
    public Boolean isDuplicatedEmail(
            String email
    ) {
        return userService.existByEmail(email);
    }
}
