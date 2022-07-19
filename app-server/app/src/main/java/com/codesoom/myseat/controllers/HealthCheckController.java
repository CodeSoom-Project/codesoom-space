package com.codesoom.myseat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HealthCheckController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "Hello";
    }
}
