package com.b1gs.controllers.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceConfigurationDto {

    private Long id;
    private String deviceId;
    private int sensorDataInterval;
    private int sensorReadInterval;
    private int fanWorkInterval;
    private int fanWorkPeriodInterval;
    private int pumpWorkInterval;
    private int humidityThreshold;
    private int fanRelayPin;
    private int pumpRelayPin;
    private int dhtSensor1;
    private int dhtSensor2;
    private int dhtSensor3;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}

