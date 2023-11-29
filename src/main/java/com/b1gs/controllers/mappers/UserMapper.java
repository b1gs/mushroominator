package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.UserDto;
import com.b1gs.controllers.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "updateDate")
    UserDto toDto(UserEntity entity);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "updateDate")
    UserEntity toEntity(UserDto dto);

}

