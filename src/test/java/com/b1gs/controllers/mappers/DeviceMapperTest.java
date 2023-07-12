package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.entity.DeviceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class DeviceMapperTest {

    private final DeviceMapper deviceMapper = Mappers.getMapper(DeviceMapper.class);

    @Test
    public void testToDto() {
        // Create a DeviceEntity
        DeviceEntity entity = new DeviceEntity();
        entity.setDeviceId("device-id");
        entity.setDescription("Test device");

        // Map the DeviceEntity to DeviceDto
        DeviceDto dto = deviceMapper.toDto(entity);

        // Assert the mapping results
        assertEquals("device-id", dto.getDeviceId());
        assertEquals("Test device", dto.getDescription());
    }

    @Test
    public void testToDtoWithNullEntity() {
        // Map a null DeviceEntity to DeviceDto
        DeviceDto dto = deviceMapper.toDto(null);

        // Assert that the result is null
        assertNull(dto);
    }

    @Test
    public void testToEntity() {
        // Create a DeviceDto
        DeviceDto dto = new DeviceDto();
        dto.setDescription("Test device");

        // Map the DeviceDto to DeviceEntity
        DeviceEntity entity = deviceMapper.toEntity(dto);

        // Assert the mapping results
        assertEquals("Test device", entity.getDescription());
        assertNotNull(entity.getDeviceId()); // Assuming DeviceEntity's deviceId is set with UUID.randomUUID().toString()
    }

    @Test
    public void testToEntityWithNullDto() {
        // Map a null DeviceDto to DeviceEntity
        DeviceEntity entity = deviceMapper.toEntity(null);

        // Assert that the result is null
        assertNull(entity);
    }
}