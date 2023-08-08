package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping(value = "/devices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createDevice(@RequestBody DeviceDto dto) {

        DeviceDto device = deviceService.createDevice(dto);

        return device.getDeviceId();
    }

    @GetMapping(value = "/devices/{deviceId}/restart")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resetDevice(@PathVariable("deviceId") String deviceId) {
        deviceService.restartDevice(deviceId);
    }
}
