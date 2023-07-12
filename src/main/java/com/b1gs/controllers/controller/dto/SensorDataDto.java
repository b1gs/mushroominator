package com.b1gs.controllers.controller.dto;

import lombok.Data;

@Data
public class SensorDataDto {

    private String deviceId;
    private int temperature;
    private int humidity;

}
