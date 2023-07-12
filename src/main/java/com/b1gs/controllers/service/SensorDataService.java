package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorDataService {

    private final SensorDataRepository repository;
    private final SensorDataMapper mapper = Mappers.getMapper(SensorDataMapper.class);

    public void createSensorData(SensorDataDto dto) {
        SensorDataEntity sensorDataEntity = mapper.toEntity(dto);

        repository.save(sensorDataEntity);
    }
}
