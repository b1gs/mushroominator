package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceConfigurationDto;
import com.b1gs.controllers.service.DeviceConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeviceConfigurationController {

    private final DeviceConfigurationService configurationService;

    @PutMapping(value = "/devices/{deviceId}/configuration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceConfigurationDto createDevice(@PathVariable("deviceId") String deviceId, @RequestBody DeviceConfigurationDto dto) {

        dto.setDeviceId(deviceId);
        return configurationService.createDeviceConfiguration(dto);
    }

    @GetMapping(value = "/devices/{deviceId}/configuration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceConfigurationDto getDeviceConfiguration(@PathVariable("deviceId") String deviceId) {
        return configurationService.getDeviceConfiguration(deviceId);
    }
}
