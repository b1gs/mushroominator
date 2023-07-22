package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.controller.dto.SensorDataList;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorDataService {

    private final SensorDataRepository repository;
    private final SensorDataMapper mapper = Mappers.getMapper(SensorDataMapper.class);

    public void createSensorData(List<SensorDataDto> sensorDataList) {
        List<SensorDataEntity> sensorDataEntities = sensorDataList.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        log.info("Created sensor data for deviceId({})", sensorDataList.get(0).getDeviceId());
        repository.saveAll(sensorDataEntities);

    }

    public Page<SensorDataList> getSensorDataBy(String deviceId, Pageable pageable) {
        log.info("Getting sensor data for deviceId({})", deviceId);
        Page<SensorDataEntity> page = repository.getAllByDeviceId(deviceId, pageable);

        List<SensorDataDto> content = page
                .getContent()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());


        List<SensorDataList> result = content.stream()
                .collect(Collectors.groupingBy(SensorDataDto::getSensorDescription))
                .values() // Get the Collection<List<SensorData>> (grouped lists)
                .stream()
                .map(SensorDataList::new)// Convert the Collection back to Stream
                .collect(Collectors.toList());

        return new PageImpl<SensorDataList>(result , pageable, page.getTotalElements());
    }

    public List<SensorDataList> getSensorDataBy(String deviceId, LocalDateTime startDate, LocalDateTime endDate) {

        return repository.getAllByDeviceIdAndCreationDateBetween(deviceId, startDate, endDate)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.groupingBy(SensorDataDto::getSensorDescription))
                .values() // Get the Collection<List<SensorData>> (grouped lists)
                .stream()
                .map(SensorDataList::new)// Convert the Collection back to Stream
                .collect(Collectors.toList());
    }
}
