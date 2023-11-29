package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.LoginRequestDto;
import com.b1gs.controllers.controller.dto.LoginResponseDto;
import com.b1gs.controllers.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponseDto authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        return authService.loginUser(loginRequest);
    }
}
