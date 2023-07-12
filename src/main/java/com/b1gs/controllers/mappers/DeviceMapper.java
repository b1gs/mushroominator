package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.DeviceEntity;
import com.b1gs.controllers.entity.SensorDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeviceMapper {

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "description", target = "description")
    DeviceDto toDto(DeviceEntity entity);

    @Mapping(expression = "java(java.util.UUID.randomUUID().toString())", target = "deviceId")
    @Mapping(source = "description", target = "description")
    DeviceEntity toEntity(DeviceDto dto);

}
