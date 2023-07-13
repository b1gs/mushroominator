package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.entity.DeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeviceMapper {

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "description", target = "description")
    DeviceDto toDto(DeviceEntity entity);

    @Mapping(expression = "java(java.util.UUID.randomUUID().toString())", target = "deviceId")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "creationDate")
    DeviceEntity toEntity(DeviceDto dto);

}
