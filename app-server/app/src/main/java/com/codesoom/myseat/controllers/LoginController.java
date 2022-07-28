package com.codesoom.myseat.controllers;

import com.codesoom.myseat.dto.LoginRequest;
import com.codesoom.myseat.dto.LoginResponse;
import com.codesoom.myseat.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인 컨트롤러
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(
        origins = "https://codesoom-project.github.io",
        allowedHeaders = "*",
        allowCredentials = "true")
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return null;
    }
}
