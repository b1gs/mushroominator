package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.CreateUserDto;
import com.b1gs.controllers.controller.dto.UserDto;
import com.b1gs.controllers.entity.UserEntity;
import com.b1gs.controllers.mappers.UserMapper;
import com.b1gs.controllers.repository.UserRepository;
import com.b1gs.controllers.service.security.Role;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(CreateUserDto userDto){
        if (userRepository.existsByEmail(userDto.getEmail())) {
            logger.info("User for email {} already exist..", userDto.getEmail());
            throw new IllegalArgumentException(String.format("Username with email %s already exists..", userDto.getEmail()));
        }

        logger.info("Creating user for email {}", userDto.getEmail());
        UserEntity entity = userMapper.toEntity(userDto);
        entity.setRoles(List.of(Role.USER));
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userMapper.toDto(userRepository.save(entity));
    }
}
