package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.CreateUserDto;
import com.b1gs.controllers.controller.dto.UserDto;
import com.b1gs.controllers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public String createDevice(@RequestBody @Valid CreateUserDto dto) {

        UserDto user = userService.createUser(dto);

        return user.getId().toString();
    }

}
