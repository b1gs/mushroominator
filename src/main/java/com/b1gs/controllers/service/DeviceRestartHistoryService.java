package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import com.b1gs.controllers.mappers.DeviceRestartHistoryMapper;
import com.b1gs.controllers.repository.DeviceRestartHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceRestartHistoryService {

    private final DeviceRestartHistoryRepository repository;
    private final DeviceRestartHistoryMapper mapper = Mappers.getMapper(DeviceRestartHistoryMapper.class);

    public DeviceRestartHistoryDto createDeviceRestartHistory(DeviceRestartHistoryDto deviceDto) {
        DeviceRestartHistoryEntity deviceEntity = mapper.toEntity(deviceDto);

        deviceEntity = repository.save(deviceEntity);

        return mapper.toDto(deviceEntity);
    }

    public List<DeviceRestartHistoryDto> findDeviceRestartHistory(String deviceId, LocalDateTime startDate, LocalDateTime endDate) {

        List<DeviceRestartHistoryEntity> result = repository.findAllByDeviceIdAndCreationDateBetween(deviceId, startDate, endDate);

        return result.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
