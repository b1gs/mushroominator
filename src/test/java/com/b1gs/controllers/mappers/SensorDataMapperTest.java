package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.entity.SensorDataEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SensorDataMapperTest {

    private final SensorDataMapper sensorDataMapper = Mappers.getMapper(SensorDataMapper.class);

    @Test
    public void testToDto() {
        // Create a SensorDataEntity
        SensorDataEntity entity = new SensorDataEntity();
        entity.setId(1L);
        entity.setDeviceId("ba306028-aecd-42fd-b9a5-f5462fde797a");
        entity.setTemperature("25");
        entity.setHumidity("50");

        // Map the SensorDataEntity to SensorDataDto
        SensorDataDto dto = sensorDataMapper.toDto(entity);

        // Assert the mapping results
        assertEquals("ba306028-aecd-42fd-b9a5-f5462fde797a", dto.getDeviceId());
        assertEquals("25", dto.getTemperature());
        assertEquals("50", dto.getHumidity());
    }

    @Test
    public void testToDtoWithNullEntity() {
        // Map a null SensorDataEntity to SensorDataDto
        SensorDataDto dto = sensorDataMapper.toDto(null);

        // Assert that the result is null
        assertNull(dto);
    }

    @Test
    public void testToEntity() {
        // Create a SensorDataDto
        SensorDataDto dto = new SensorDataDto();
        dto.setDeviceId("ba306028-aecd-42fd-b9a5-f5462fde797a");
        dto.setTemperature("25");
        dto.setHumidity("50");

        // Map the SensorDataDto to SensorDataEntity
        SensorDataEntity entity = sensorDataMapper.toEntity(dto);

        // Assert the mapping results
        assertEquals("ba306028-aecd-42fd-b9a5-f5462fde797a", entity.getDeviceId());
        assertEquals("25", entity.getTemperature());
        assertEquals("50", entity.getHumidity());
    }

    @Test
    public void testToEntityWithNullDto() {
        // Map a null SensorDataDto to SensorDataEntity
        SensorDataEntity entity = sensorDataMapper.toEntity(null);

        // Assert that the result is null
        assertNull(entity);
    }
}