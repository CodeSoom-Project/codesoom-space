package com.codesoom.myseat.controllers.login;

import com.codesoom.myseat.domain.User;
import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.dto.LoginResponse;
import com.codesoom.myseat.services.auth.AuthenticationService;
import com.codesoom.myseat.services.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** 로그인 컨트롤러 */
@RestController
@RequestMapping("/login")
@CrossOrigin
@Slf4j
public class LoginController {
    
    private final AuthenticationService authService;
    
    private final UserService userService;

    public LoginController(
            AuthenticationService authService,
            UserService userService
    ) {
        this.authService = authService;
        this.userService = userService;
    }

    /**
     * 로그인 후 로그인 응답 정보를 반환한다.
     * 
     * @param request 로그인 폼에 입력된 데이터
     * @return LoginResponse 로그인 응답 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userService.findByEmail(email);

        String accessToken = authService.login(user, password);
        log.info("accessToken: " + accessToken);

        String name = user.getName();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .name(name)
                .build();
    }
    
}
