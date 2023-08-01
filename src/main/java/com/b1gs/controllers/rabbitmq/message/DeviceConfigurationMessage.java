package com.b1gs.controllers.rabbitmq.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude = {"brokerIp", "brokerPort"})
public class DeviceConfigurationMessage {

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
    private String brokerIp;
    private int brokerPort;

}
