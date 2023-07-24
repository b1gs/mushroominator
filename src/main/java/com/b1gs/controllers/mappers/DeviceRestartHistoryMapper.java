package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeviceRestartHistoryMapper {

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(source = "creationDate", target = "creationDate")
    DeviceRestartHistoryDto toDto(DeviceRestartHistoryEntity entity);

    @Mapping(source = "deviceId", target = "deviceId")
    @Mapping(expression = "java(java.time.LocalDateTime.now())", target = "creationDate")
    DeviceRestartHistoryEntity toEntity(DeviceRestartHistoryDto dto);

}
