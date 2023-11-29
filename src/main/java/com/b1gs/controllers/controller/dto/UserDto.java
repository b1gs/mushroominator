package com.b1gs.controllers.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Valid
public class UserDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Email must be from 5 to 100 characters")
    private String email;

    @NotEmpty
    @Size(min = 1, max = 100, message = "First name must be from 1 to 100 characters")
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 100, message = "First name must be from 1 to 100 characters")
    private String lastName;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
