package com.b1gs.controllers.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceRestartHistoryDto {

    private String deviceId;
    private LocalDateTime creationDate;

}
