package com.b1gs.controllers.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class CreateUserDto extends UserDto {

    @NotEmpty
    @Size(max = 255, message = "Password hash up to 255 chars")
    private String password;

}
