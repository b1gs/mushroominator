package com.b1gs.controllers.controller;

import com.b1gs.controllers.controller.dto.DeviceDto;
import com.b1gs.controllers.controller.dto.SensorDataDto;
import com.b1gs.controllers.service.DeviceService;
import com.b1gs.controllers.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;
    private final DeviceService deviceService;

    @GetMapping(value = "/sensor-data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<SensorDataDto> getSensorData(@RequestParam(name = "deviceId") String deviceId,
                                             @RequestParam(name = "page") int pageNum,
                                             @RequestParam(name = "size") int pageSize,
                                             @RequestParam(name = "sortProperty", required = false) String sortProperty,
                                             @RequestParam(name = "sortDirection", required = false) String sortDirection) {
        Pageable pageable;
        if (sortProperty != null ) {
            Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, sortProperty);
            pageable = PageRequest.of(pageNum, pageSize, sort);
        }else {
            pageable = PageRequest.of(pageNum, pageSize);
        }

        return sensorDataService.getSensorDataBy(deviceId, pageable);
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
