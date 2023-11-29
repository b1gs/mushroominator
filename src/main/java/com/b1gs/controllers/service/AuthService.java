package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.LoginRequestDto;
import com.b1gs.controllers.controller.dto.LoginResponseDto;
import com.b1gs.controllers.entity.UserEntity;
import com.b1gs.controllers.repository.UserRepository;
import com.b1gs.controllers.service.security.JwtTokenProvider;
import com.b1gs.controllers.service.security.Role;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public LoginResponseDto loginUser(LoginRequestDto loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        new UserPrincipal(loginRequest.getEmail()),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        List<String> roles = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toList());

        return new LoginResponseDto(jwt, user.getEmail(), roles);
    }
}
