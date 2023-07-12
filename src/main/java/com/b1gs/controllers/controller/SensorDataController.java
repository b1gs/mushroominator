package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;
    private final SensorDataMapper mapper = Mappers.getMapper(SensorDataMapper.class);

    @PostMapping(value = "/sensor-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String supplySensorData(@RequestBody SensorDataDto dto) {

        SensorDataEntity sensorDataEntity = mapper.toEntity(dto);
        System.out.println(sensorDataEntity.toString());
        sensorDataService.createSensorData(sensorDataEntity);
        return sensorDataEntity.getId().toString();
    }

}
