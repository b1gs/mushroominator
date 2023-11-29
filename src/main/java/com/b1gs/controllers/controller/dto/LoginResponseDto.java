package com.b1gs.controllers.controller.dto;


import java.util.List;


public record LoginResponseDto(String token, String email, List<String> roles) {

}
