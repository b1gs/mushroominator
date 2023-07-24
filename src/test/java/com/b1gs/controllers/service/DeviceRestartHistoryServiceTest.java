package com.b1gs.controllers.service;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.entity.DeviceRestartHistoryEntity;
import com.b1gs.controllers.mappers.DeviceRestartHistoryMapper;
import com.b1gs.controllers.mappers.DeviceRestartHistoryMapperImpl;
import com.b1gs.controllers.repository.DeviceRestartHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class DeviceRestartHistoryServiceTest {

    @Mock
    private DeviceRestartHistoryRepository repository;

    @InjectMocks
    private DeviceRestartHistoryService deviceRestartHistoryService;

    private DeviceRestartHistoryMapper mapper = new DeviceRestartHistoryMapperImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDeviceRestartHistory() {
        DeviceRestartHistoryDto dto = new DeviceRestartHistoryDto(/* Initialize with required fields */);
        dto.setDeviceId("deviceId");

        DeviceRestartHistoryEntity entity = mapper.toEntity(dto);

        when(repository.save(any(DeviceRestartHistoryEntity.class))).thenReturn(entity);

        DeviceRestartHistoryDto resultDto = deviceRestartHistoryService.createDeviceRestartHistory(dto);

        assertEquals(dto.getDeviceId(), resultDto.getDeviceId()); // Replace getProperty1() with the appropriate property getter method
        assertNotNull(resultDto.getCreationDate()); // Replace getProperty2() with the appropriate property getter method
        // Add more assertions for other properties as needed

        verify(repository, times(1)).save(any(DeviceRestartHistoryEntity.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testFindDeviceRestartHistory() {
        String deviceId = "your_device_id";
        LocalDateTime startDate = LocalDateTime.parse("2023-07-24T12:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2023-07-25T12:00:00");

        List<DeviceRestartHistoryEntity> mockResult = new ArrayList<>(); // Initialize with mock data

        when(repository.findAllByDeviceIdAndCreationDateBetween(deviceId, startDate, endDate))
                .thenReturn(mockResult);

        List<DeviceRestartHistoryDto> resultDtoList = deviceRestartHistoryService.findDeviceRestartHistory(deviceId, startDate, endDate);

        assertEquals(mockResult.size(), resultDtoList.size());

        // Add more assertions comparing properties between the mockResult and resultDtoList as needed

        verify(repository, times(1)).findAllByDeviceIdAndCreationDateBetween(deviceId, startDate, endDate);
        verifyNoMoreInteractions(repository);
    }

}