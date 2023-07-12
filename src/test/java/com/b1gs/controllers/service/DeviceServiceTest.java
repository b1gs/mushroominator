package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.entity.DeviceEntity;
import com.b1gs.controllers.mappers.DeviceMapper;
import com.b1gs.controllers.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private final DeviceMapper deviceMapper = Mappers.getMapper(DeviceMapper.class);

    @Test
    public void testCreateDevice() {
        // Prepare a DeviceDto for the input
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDescription("Test device");

        // Prepare a DeviceEntity for the repository save method
        DeviceEntity deviceEntitySaved = new DeviceEntity();
        deviceEntitySaved.setDeviceId("ba306028-aecd-42fd-b9a5-f5462fde797a");
        deviceEntitySaved.setDescription("Test device");

        // Mock the repository.save method to return the saved DeviceEntity
        when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(deviceEntitySaved);

        // Call the createDevice method
        DeviceDto createdDeviceDto = deviceService.createDevice(deviceDto);

        // Verify the repository.save method is called with the correct DeviceEntity
        Mockito.verify(deviceRepository, times(1)).save(any(DeviceEntity.class));

        // Verify the mapping results
        assertEquals("ba306028-aecd-42fd-b9a5-f5462fde797a", createdDeviceDto.getDeviceId());
        assertEquals("Test device", createdDeviceDto.getDescription());
    }
}