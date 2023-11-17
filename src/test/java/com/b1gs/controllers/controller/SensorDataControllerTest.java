package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.service.SensorDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SensorDataControllerTest {

    @Mock
    private SensorDataService sensorDataService;

    @InjectMocks
    private SensorDataController sensorDataController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorDataController).build();
    }

    @Test
    public void testSupplySensorData() throws Exception {
        // Prepare a SensorDataDto for the request body
        SensorDataDto sensorDataDto = new SensorDataDto();
        sensorDataDto.setDeviceId("device-id");
        sensorDataDto.setTemperature("25");
        sensorDataDto.setHumidity("50");

        // Perform a POST request to /sensor-data
        mockMvc.perform(MockMvcRequestBuilders.post("/sensor-data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"deviceId\":\"device-id\",\"temperature\":25,\"humidity\":50, \"description\":\"b1gs sensor\"}]"))
                .andExpect(status().isCreated());

        // Verify that the createSensorData method is called with the provided SensorDataDto
        verify(sensorDataService).createSensorData(List.of(sensorDataDto));
    }

}