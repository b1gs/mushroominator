package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.service.DeviceService;
import com.b1gs.controllers.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;
    private final DeviceService deviceService;

    @GetMapping(value = "/sensor-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<SensorDataDto> getSensorData(@RequestParam(name = "deviceId") String deviceId) {

        return sensorDataService.getSensorDataBy(deviceId);
    }

    @PostMapping(value = "/sensor-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void supplySensorData(@RequestBody SensorDataDto dto) {

        sensorDataService.createSensorData(dto);
    }

    @PostMapping(value = "/devices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createDevice(@RequestBody DeviceDto dto) {

        DeviceDto device = deviceService.createDevice(dto);

        return device.getDeviceId();
    }

}
