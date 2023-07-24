package com.b1gs.controllers.mappers;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceRestartHistoryMapperTest {

    private DeviceRestartHistoryMapper mapper = Mappers.getMapper(DeviceRestartHistoryMapper.class);

    @Test
    public void testToDto() {
        DeviceRestartHistoryEntity entity = new DeviceRestartHistoryEntity();
        entity.setDeviceId("test_device_id");
        entity.setCreationDate(LocalDateTime.parse("2023-07-24T12:00:00"));

        DeviceRestartHistoryDto dto = mapper.toDto(entity);

        assertEquals(entity.getDeviceId(), dto.getDeviceId());
        assertEquals(entity.getCreationDate(), dto.getCreationDate());
    }

    @Test
    public void testToEntity() {
        DeviceRestartHistoryDto dto = new DeviceRestartHistoryDto();
        dto.setDeviceId("test_device_id");

        DeviceRestartHistoryEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getDeviceId(), entity.getDeviceId());
        assertNotNull(entity.getCreationDate());
    }

}