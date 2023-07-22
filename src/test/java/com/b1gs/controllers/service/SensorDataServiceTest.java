package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import com.b1gs.controllers.mappers.SensorDataMapper;
import com.b1gs.controllers.repository.SensorDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SensorDataServiceTest {

    @Mock
    private SensorDataRepository sensorDataRepository;

    @InjectMocks
    private SensorDataService sensorDataService;

    private final SensorDataMapper sensorDataMapper = Mappers.getMapper(SensorDataMapper.class);

    @Test
    public void testCreateSensorData() {
        // Prepare a SensorDataDto for the input
        SensorDataDto sensorDataDto = new SensorDataDto();
        sensorDataDto.setDeviceId("device-id");
        sensorDataDto.setTemperature("25");
        sensorDataDto.setHumidity("50");

        // Prepare a SensorDataEntity for the repository save method
        SensorDataEntity sensorDataEntityToSave = sensorDataMapper.toEntity(sensorDataDto);

        // Call the createSensorData method
        sensorDataService.createSensorData(List.of(sensorDataDto));

        // Verify the repository.save method is called with the correct SensorDataEntity
        verify(sensorDataRepository, times(1)).save(sensorDataEntityToSave);
    }
}