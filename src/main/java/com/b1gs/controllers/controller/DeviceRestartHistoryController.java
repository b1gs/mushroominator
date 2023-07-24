package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceRestartHistoryDto;
import com.b1gs.controllers.service.DeviceRestartHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeviceRestartHistoryController {

    private final DeviceRestartHistoryService deviceRestartHistoryService;

    @PostMapping(value = "/devices-restart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void submitDeviceRestart(@RequestBody DeviceRestartHistoryDto dto) {
        deviceRestartHistoryService.createDeviceRestartHistory(dto);
    }

    @GetMapping(value = "/devices-restart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<DeviceRestartHistoryDto> getDeviceRestarts(@RequestParam("deviceId") String deviceId,
                                                           @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDateTime,
                                                           @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDateTime) {
        return deviceRestartHistoryService.findDeviceRestartHistory(deviceId, startDateTime, endDateTime);
    }
}
