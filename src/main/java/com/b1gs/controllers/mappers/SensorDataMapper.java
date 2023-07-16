package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SensorDataMapper {

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDescription", target = "sensorDescription")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    @Mapping(source = "creationDate", target = "creationDate")
    SensorDataDto toDto(SensorDataEntity entity);

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "sensorDescription", target = "sensorDescription")
    @Mapping(source = "temperature", target = "temperature")
    @Mapping(source = "humidity", target = "humidity")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "creationDate")
    SensorDataEntity toEntity(SensorDataDto dto);

}
