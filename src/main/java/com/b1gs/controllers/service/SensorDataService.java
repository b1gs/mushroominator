package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorDataService {

    private final SensorDataRepository repository;
    private final SensorDataMapper mapper = Mappers.getMapper(SensorDataMapper.class);

    public void createSensorData(SensorDataDto dto) {
        SensorDataEntity sensorDataEntity = mapper.toEntity(dto);

        repository.save(sensorDataEntity);
    }

    public List<SensorDataDto> getSensorDataBy(String deviceId) {
        log.info("Getting sensor data for deviceId({})", deviceId);
        return repository.getAllByDeviceIdOrderByCreationDateDesc(deviceId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
