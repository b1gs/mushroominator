package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    public void testCreateDevice() throws Exception {
        // Prepare a DeviceDto for the request body and response
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setDeviceId("device-id");

        // Mock the deviceService.createDevice method to return the DeviceDto
        when(deviceService.createDevice(any(DeviceDto.class))).thenReturn(deviceDto);

        // Perform a POST request to /devices
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Test device\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        // Verify that the createDevice method is called with the provided DeviceDto
        verify(deviceService).createDevice(any(DeviceDto.class));

        // Verify the response body contains the device ID
        String responseBody = result.getResponse().getContentAsString();
        assertEquals("device-id", responseBody);
    }
}