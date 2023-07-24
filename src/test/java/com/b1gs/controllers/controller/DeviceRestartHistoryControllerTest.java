package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.service.DeviceRestartHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeviceRestartHistoryControllerTest {

    @Mock
    private DeviceRestartHistoryService deviceRestartHistoryService;

    @InjectMocks
    private DeviceRestartHistoryController deviceRestartHistoryController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deviceRestartHistoryController).build();
    }

    @Test
    public void testSubmitDeviceRestart() throws Exception {
        DeviceRestartHistoryDto dto = new DeviceRestartHistoryDto(/* Initialize with required fields */);

        mockMvc.perform(MockMvcRequestBuilders.post("/devices-restart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isCreated());

        verify(deviceRestartHistoryService, times(1)).createDeviceRestartHistory(any(DeviceRestartHistoryDto.class));
        verifyNoMoreInteractions(deviceRestartHistoryService);
    }

    @Test
    public void testGetDeviceRestarts() throws Exception {
        String deviceId = "your_device_id";
        LocalDateTime startDateTime = LocalDateTime.parse("2023-07-24T12:00:00");
        LocalDateTime endDateTime = LocalDateTime.parse("2023-07-25T12:00:00");

        List<DeviceRestartHistoryDto> mockResult = new ArrayList<>(); // Initialize with mock data

        when(deviceRestartHistoryService.findDeviceRestartHistory(deviceId, startDateTime, endDateTime))
                .thenReturn(mockResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/devices-restart")
                .header("Content-Type", "application/json")
                .param("deviceId", deviceId)
                .param("startDate", "2023-07-24T12:00:00")
                .param("endDate", "2023-07-25T12:00:00"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(mockResult)));

        verify(deviceRestartHistoryService, times(1)).findDeviceRestartHistory(deviceId, startDateTime, endDateTime);
        verifyNoMoreInteractions(deviceRestartHistoryService);
    }

    // Helper method to convert objects to JSON strings
    private static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}