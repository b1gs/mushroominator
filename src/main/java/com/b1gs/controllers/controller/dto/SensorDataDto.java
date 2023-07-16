package com.b1gs.controllers.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SensorDataDto {

    private String deviceId;
    private String sensorDescription;
    private int temperature;
    private int humidity;
    private LocalDateTime creationDate;

}
