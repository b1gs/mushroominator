package com.b1gs.controllers.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "device_configuration", schema = "mushroominator")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class DeviceConfigurationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "sensor_data_interval")
    private int sensorDataInterval;

    @Column(name = "sensor_read_interval")
    private int sensorReadInterval;

    @Column(name = "fan_work_interval")
    private int fanWorkInterval;

    @Column(name = "fan_work_period_interval")
    private int fanWorkPeriodInterval;

    @Column(name = "pump_work_interval")
    private int pumpWorkInterval;

    @Column(name = "humidity_threshold")
    private int humidityThreshold;

    @Column(name = "fan_relay_pin")
    private int fanRelayPin;

    @Column(name = "pump_relay_pin")
    private int pumpRelayPin;

    @Column(name = "dht_sensor_1")
    private int dhtSensor1;

    @Column(name = "dht_sensor_2")
    private int dhtSensor2;

}
