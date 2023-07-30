package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceConfigurationDto;
import com.b1gs.controllers.service.DeviceConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceConfigurationController {

    private final DeviceConfigurationService configurationService;

    @PutMapping(value = "/devices-configuration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceConfigurationDto createDevice(@RequestBody DeviceConfigurationDto dto) {
        return configurationService.createDeviceConfiguration(dto);
    }

}
