package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.entity.DeviceEntity;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.DeviceMapper;
import com.b1gs.controllers.repository.DeviceRepository;
import com.b1gs.controllers.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;
    private final DeviceMapper mapper = Mappers.getMapper(DeviceMapper.class);

    public DeviceDto createDevice(DeviceDto deviceDto) {
        DeviceEntity deviceEntity = mapper.toEntity(deviceDto);

        deviceEntity = repository.save(deviceEntity);

        return mapper.toDto(deviceEntity);
    }
}
